package com.blogHubAdvisor.bloghub_advisor_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "advisors")
public class AdvisorModel {



    @Id
    @Column(name="blog_email",unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "blog_categories", joinColumns = @JoinColumn(name = "email"))
    @Column(name = "blog_category")
    private List<String> blogCategory = new ArrayList<>();





}
