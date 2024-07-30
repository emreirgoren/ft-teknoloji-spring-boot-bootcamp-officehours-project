package com.patika.bloghubservice.client.advisor;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "advisor-service", url = "localhost:8083/api/v1/advisors")
public interface AdvisorClient {

    @PostMapping("/{email}/{blogCategory}")
    void sendData(@PathVariable String email,@PathVariable String blogCategory);


    @GetMapping
    List<String> getAdvisor();

}
