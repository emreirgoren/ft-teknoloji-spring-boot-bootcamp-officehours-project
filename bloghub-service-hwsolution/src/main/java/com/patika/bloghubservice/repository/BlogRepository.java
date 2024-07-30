package com.patika.bloghubservice.repository;


import com.patika.bloghubservice.exception.BlogNotFoundException;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BlogRepository {

    private Map<String, Blog> blogMap = new HashMap<>();

    public void save(Blog blog) {
        blogMap.put(blog.getTitle(), blog);
    }

    public Optional<Blog> findByTitle(String title) {
        return Optional.ofNullable(blogMap.values()
                .stream()
                .filter(blog -> blog.getTitle().equals(title))
                .filter(blog -> !blog.getBlogStatus().equals(BlogStatus.DELETED))
                .findFirst().orElseThrow(() -> new BlogNotFoundException()));
    }

    public List<Blog> findAll() {
        return blogMap.values().stream().toList();
    }

    public void addComment(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);
    }

    public void likeBlog(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);
    }

    public List<Blog> getAllBlog(Optional<User> user) {

        List<Blog> blogList = blogMap.values().stream()
                .filter(blog -> blog.getUser().equals(user))
                .toList();
        return blogList;
    }

    public void hardDelete(Optional<User> foundUser) {

        blogMap.values()
                .removeIf(blog -> blog.getUser().getEmail().equalsIgnoreCase(foundUser.get().getEmail()) &&
                        blog.getBlogStatus().equals(BlogStatus.DRAFT));

    }

}
