package com.patika.bloghubservice.client.advisor.service;

import com.patika.bloghubservice.client.advisor.AdvisorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvisorClientService {

    private final AdvisorClient advisorClient;


    public void sendData(String email,String blogCategory){
        advisorClient.sendData(email,blogCategory);
    }

    public List<String> getAdvisor(){
        return advisorClient.getAdvisor();
    }

}
