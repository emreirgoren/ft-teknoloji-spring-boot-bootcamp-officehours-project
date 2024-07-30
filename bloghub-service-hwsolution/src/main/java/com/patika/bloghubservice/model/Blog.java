package com.patika.bloghubservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.patika.bloghubservice.model.enums.BlogStatus;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blog {

    private String title;
    private String text;
    private LocalDateTime createdDate;

    @JsonBackReference
    private User user;
    private BlogStatus blogStatus;
    private Long likeCount;
    private List<BlogComment> blogCommentList = new ArrayList<>();
    private byte[] imageBytes;
    private String imageURL;
    private String blogCategory;

    public Blog(String title, String text, User user,String blogCategory) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.blogStatus = BlogStatus.DRAFT;
        this.likeCount = 0L;
        this.blogCategory=blogCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogStatus getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(BlogStatus blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public List<BlogComment> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(List<BlogComment> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(String blogCategory) {
        this.blogCategory = blogCategory;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", blogStatus=" + blogStatus +
                ", likeCount=" + likeCount +
                ", blogCommentList=" + blogCommentList +
                ", imageBytes=" + Arrays.toString(imageBytes) +
                ", imageURL='" + imageURL + '\'' +
                ", blogCategory='" + blogCategory + '\'' +
                '}';
    }
}
