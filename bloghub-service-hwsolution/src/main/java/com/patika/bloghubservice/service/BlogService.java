package com.patika.bloghubservice.service;

import com.patika.bloghubservice.client.advisor.dto.AdvisorData;
import com.patika.bloghubservice.client.advisor.service.AdvisorClientService;
import com.patika.bloghubservice.converter.BlogConverter;
import com.patika.bloghubservice.dto.request.BlogSaveRequest;
import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.exception.*;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.BlogComment;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.repository.BlogRepository;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final AdvisorClientService advisorClientService;

    public BlogResponse createBlog(String email, BlogSaveRequest request) {

        //ödev: kullanıcı blog yayınlamak için approved bir statuye sahip olmalı

        Optional<User> foundUser = userRepository.findByEmail(email);
        List<Blog> blogList = new ArrayList<>();

        Blog blog = new Blog(request.getTitle(), request.getText(), foundUser.get(),request.getCategory());
        blog.setUser(foundUser.get());
        blogList.add(blog);
        foundUser.get().setBlogList(blogList);
        blog.setBlogCategory(request.getCategory());
        blogRepository.save(blog);

        return BlogConverter.toResponse(blog);
    }

    public Blog getBlogByTitle(String title) {
        return blogRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("blog bulunamadı"));
    }

    public void addComment(String title, String email, String comment) {

        Blog foundBlog = getBlogByTitle(title);

        Optional<User> user = userRepository.findByEmail(email);

        BlogComment blogComment = new BlogComment(user.get(), comment);

        foundBlog.getBlogCommentList().add(blogComment);

        blogRepository.addComment(title, foundBlog);

    }

    public List<Blog> getBlogsFilterByStatus(BlogStatus blogStatus, String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        return foundUser.get().getBlogList().stream()
                .filter(blog -> blogStatus.equals(blog.getBlogStatus()))
                .toList();
    }

    public void changeBlogStatus(BlogStatus blogStatus, String title) {

        Blog foundBlog = getBlogByTitle(title);

        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new RuntimeException("statüsü PUBLISHED olan bir blog silinemez.");
        }

        foundBlog.setBlogStatus(blogStatus);

    }

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }
/*
    public void likeBlog(String title, String email) {
        Blog blog = getBlogByTitle(title);

        blog.setLikeCount(blog.getLikeCount() + 1);

        blogRepository.likeBlog(title, blog);
    }
*/

    /**
     * Refactor edilmemiş kod yukardadır.
     * like-count endpoint’ini bir kullanıcın en fazla 50 kere beğenibileceği şekilde refactor edin
     */

    public BlogResponse likeBlog(String title, String email) {

        Blog blog = getBlogByTitle(title);
        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().getVisitedBlogCategoryList().add(blog.getBlogCategory());

        if (blog.getLikeCount() == 50) {
            throw new LikeLimitExceededException();
        }
        blog.setLikeCount(blog.getLikeCount() + 1);
        BlogResponse blogResponse = BlogResponse.builder()
                .title(blog.getTitle())
                .text(blog.getText())
                .createdDateTime(blog.getCreatedDate())
                .blogStatus(blog.getBlogStatus())
                .likeCount(blog.getLikeCount())
                .build();
        return blogResponse;

    }

    public Long getLikeCountByTitle(String title) {

        Blog blog = getBlogByTitle(title);

        return blog.getLikeCount();
    }



    /**
     * change blog status 2
     */

    public BlogResponse changeBlogStatus2(String title, BlogStatus blogStatus) {

        Blog foundBlog = getBlogByTitle(title);
        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new ChangeBlogStatusException();
        }else if ( foundBlog.getUser().getStatusType().equals(StatusType.WAITING_APPROVAL) ||
                    foundBlog.getUser().getStatusType().equals(StatusType.WAITING_APPROVAL)){
            throw new UnauthorizedUserException();
        }
        blogRepository.findByTitle(foundBlog.getTitle()).get().setBlogStatus(blogStatus);


        Blog blog = blogRepository.findByTitle(foundBlog.getTitle()).get();

        BlogResponse blogResponse = BlogResponse.builder()
                .title(blog.getTitle())
                .text(blog.getText())
                .blogStatus(blog.getBlogStatus())
                .build();
        return blogResponse;

    }

    /**
     * Kullanıcı kendi PUBLISHED ve DRAFT olan blog’larını getiren endpointi yazın
     */

    public List<Blog> getPublishedAndDraftBlogs(String email) {

        //List<BlogResponse> blogResponseList = new ArrayList<>();
        Optional<User> foundUser = userRepository.findByEmail(email);

        if (foundUser.isEmpty()) {
            throw new UserNotFoundException();
        } else if (blogRepository.findAll().size() == 0) {
            throw new RuntimeException();
        } else {
            return blogRepository.findAll().stream()
                    .filter(blog -> blog.getUser().getEmail().equals(foundUser.get().getEmail()))
                    .filter(blog -> blog.getBlogStatus().equals(BlogStatus.PUBLISHED) || blog.getBlogStatus().equals(BlogStatus.DRAFT))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Kullanıcı kendi blog’larından DRAFT statüsünde olanları hard delete ile silebilir
     */
    public List<Blog> hardDelete(String email) {


        Optional<User> foundUser = userRepository.findByEmail(email);

        if (foundUser.isEmpty()) {
            throw new UserNotFoundException();
        } else if (foundUser.get().getBlogList().isEmpty()) {
            throw new BlogNotFoundException();
        }
        blogRepository.hardDelete(foundUser);

        return blogRepository.findAll();

    }

    public BlogResponse saveBlogImage(String email, BlogResponse request) {

        Optional<User> foundUser = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException()));

        Optional<Blog> foundBlog = blogRepository.findByTitle(request.getTitle());

        foundBlog.get().setImageBytes(request.getImageURL().getBytes(StandardCharsets.UTF_8));
        foundBlog.get().setImageURL(request.getImageURL());

        return BlogResponse.builder()
                .title(foundBlog.get().getTitle())
                .text(foundBlog.get().getText())
                .imageBytes(foundBlog.get().getImageBytes())
                .imageURL(foundBlog.get().getImageURL())
                .build();
    }

    /**
     *
     */
    public BlogResponse getBlog(String email,String title){

        Optional<User> foundUser = userRepository.findByEmail(email);
        Optional<Blog> foundBlog = blogRepository.findByTitle(title);

        if(foundUser.isEmpty()){
            throw new UserNotFoundException();
        }
        if (foundBlog.isEmpty()){
            throw new BlogNotFoundException();
        }

        String blogCategory = foundBlog.get().getBlogCategory();
        String foundEmail =foundUser.get().getEmail();
        /**
         * veri gönder
         *
         * email
         * blogCategory
         * foundUser.get().getEmail(),
         */
        advisorClientService.sendData(foundEmail,blogCategory);


        return BlogResponse.builder()
                .title(foundBlog.get().getTitle())
                .blogCategory(foundBlog.get().getBlogCategory())
                .text(foundBlog.get().getText())
                .createdDateTime(foundBlog.get().getCreatedDate())
                .build();

    }

    /**
     *
     */

    public List<String> getAdvisor(){
        return advisorClientService.getAdvisor();
    }

}
