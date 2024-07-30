package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.client.advisor.AdvisorClient;
import com.patika.bloghubservice.dto.request.BlogSaveRequest;
import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.service.BlogService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final AdvisorClient advisorClient;

    @PostMapping("/users/{email}")
    public GenericResponse<BlogResponse> createBlog(@RequestBody BlogSaveRequest request, @PathVariable String email) {
        return GenericResponse.success(blogService.createBlog(email, request), HttpStatus.CREATED);
    }

    @GetMapping
    public GenericResponse<List<Blog>> getAllBlogs() {
        return GenericResponse.success(blogService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public Blog getBlogByEmail(@PathVariable String title) {
        return blogService.getBlogByTitle(title);
    }

    @PutMapping("/{title}/users/{email}")
    public void addComment(@PathVariable String title, @PathVariable String email, @RequestBody String comment) {
        blogService.addComment(title, email, comment);
    }
/*
    @PutMapping("/{title}/users/{email}/like-count")
    public void likeBlog(@PathVariable String title, @PathVariable String email) {
        //bir kullanıcı sadece maksimum 50 kere beğenebilir
        blogService.likeBlog(title, email);
    }
*/

    /**
     * like-count endpoint’ini bir kullanıcın en fazla 50 kere beğenibileceği şekilde refactor edin
     */

    @PutMapping("/{title}/users/{email}/like-count")
    public BlogResponse likeBlog(@PathVariable String title, @PathVariable String email) {
        return blogService.likeBlog(title, email);
    }


    @GetMapping("/{title}/like-count")
    public Long getLikeCountByTitle(@PathVariable String title) {
        return blogService.getLikeCountByTitle(title);
    }
//commentleri getiren end-point

    //kullanıcı sadece kendi blog'larını gören endpoint

    // resim yükleme

    /**
     * change blog status
     */

    @PutMapping("/{title}")
    public BlogResponse changeBlogStatus2(@PathVariable String title, @PathParam("blogStatus") BlogStatus blogStatus) {
        return blogService.changeBlogStatus2(title, blogStatus);
    }

    /**
     * Kullanıcı kendi PUBLISHED ve DRAFT olan blog’larını getiren endpointi yazın
     */

    @GetMapping("/{email}/published-draft")
    public List<Blog> getPublishedAndDraftBlogs(@PathVariable String email) {

        return blogService.getPublishedAndDraftBlogs(email);
    }

    /**
     * Kullanıcı kendi blog’larından DRAFT statüsünde olanları hard delete ile silebilir
     */

    @DeleteMapping("/{email}/hardDelete")
    public List<Blog> hardDeleteBlogs(@PathVariable String email) {
        return blogService.hardDelete(email);
    }

    /**
     * Kullanıcı resim ekleme
     */

    @PostMapping("/{email}")
    public BlogResponse saveBlogImage(@PathVariable String email, @RequestBody BlogResponse request) {
        return blogService.saveBlogImage(email, request);
    }


    /**
     *
     */

    @GetMapping("/getBlog/{email}/{title}")
    public BlogResponse getBlog(@PathVariable String email,@PathVariable String title){
        return blogService.getBlog(email,title);
    }

    @GetMapping("/getAdvisor")
    public List<String> getAdvisor(){
        return advisorClient.getAdvisor();
    }

}
