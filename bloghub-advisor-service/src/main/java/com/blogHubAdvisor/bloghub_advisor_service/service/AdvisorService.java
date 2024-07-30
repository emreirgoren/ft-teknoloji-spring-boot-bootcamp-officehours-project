package com.blogHubAdvisor.bloghub_advisor_service.service;

import com.blogHubAdvisor.bloghub_advisor_service.model.AdvisorModel;
import com.blogHubAdvisor.bloghub_advisor_service.repository.AdvisorRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvisorService {

    private AdvisorRepository advisorRepository;
    private PineconeService pineconeService;

    public AdvisorService(AdvisorRepository advisorRepository, PineconeService pineconeService) {
        this.advisorRepository = advisorRepository;
        this.pineconeService = pineconeService;
    }

    public void createData(String email, String blogCategory) {

        pineconeService.addVector("emre@gmail.com", List.of("gezi","yemek"));

        Optional<AdvisorModel> advisorModel = advisorRepository.findById(email);

        if (advisorModel.isEmpty()) {
            AdvisorModel newAdvisorModel = new AdvisorModel();
            newAdvisorModel.setEmail(email);
            newAdvisorModel.setBlogCategory(new ArrayList<>());
            newAdvisorModel.getBlogCategory().add(blogCategory);
            advisorRepository.save(newAdvisorModel);
        } else {
            AdvisorModel existingModel = advisorRepository.findById(email).get();

            if (existingModel.getBlogCategory() == null) {
                existingModel.setBlogCategory(new ArrayList<>());
            }
            if (!existingModel.getBlogCategory().contains(blogCategory)) {
                existingModel.getBlogCategory().add(blogCategory);
                advisorRepository.save(existingModel);
            }
        }
    }








}
