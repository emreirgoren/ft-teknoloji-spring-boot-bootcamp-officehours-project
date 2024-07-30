package com.blogHubAdvisor.bloghub_advisor_service.repository;

import com.blogHubAdvisor.bloghub_advisor_service.model.AdvisorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorModel,String> {



}
