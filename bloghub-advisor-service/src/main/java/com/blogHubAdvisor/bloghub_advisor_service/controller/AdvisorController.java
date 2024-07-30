package com.blogHubAdvisor.bloghub_advisor_service.controller;


import com.blogHubAdvisor.bloghub_advisor_service.service.AdvisorService;
import com.blogHubAdvisor.bloghub_advisor_service.service.PineconeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advisors")
public class AdvisorController {

    private final AdvisorService advisorService;

    private final PineconeService pineconeService;

    public AdvisorController(AdvisorService advisorService, PineconeService pineconeService) {
        this.advisorService = advisorService;
        this.pineconeService = pineconeService;
    }

    @PostMapping("/{email}/{blogCategory}")
    public void createData(@PathVariable String email,@PathVariable String blogCategory){
        advisorService.createData(email, blogCategory);
    }

    @PostMapping()
    public void addVector(){
        pineconeService.addVector("emre@gmail.com",List.of("gezi","yemek"));
    }



}
