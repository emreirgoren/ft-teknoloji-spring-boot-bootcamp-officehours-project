package com.blogHubAdvisor.bloghub_advisor_service.service;

import com.blogHubAdvisor.bloghub_advisor_service.config.RestTemplateConfig;
import com.blogHubAdvisor.bloghub_advisor_service.util.CategoriesToVector;
import io.pinecone.clients.Pinecone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PineconeService {

    @Value("${pinecone.api.url}")
    private String apiURL;

    @Value("${pinecone.api.key}")
    private String apiKEY;


    private final RestTemplateConfig restTemplateConfig;

    private final RestTemplate restTemplate;
    private final CategoriesToVector categoriesVector;

    public PineconeService(RestTemplateConfig restTemplateConfig, RestTemplate restTemplate, CategoriesToVector categoriesToVector) {
        this.restTemplateConfig = restTemplateConfig;
        this.restTemplate = restTemplate;
        this.categoriesVector = categoriesToVector;
    }


    public void addVector(String email, List<String> categories) {


        String url = apiURL + "/vectors/upsert";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKEY);
        headers.set("Content-Type", "application/json");

        double[] vector = categoriesVector.categoriesToVector(categories);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", email);
        requestBody.put("values", vector);
        requestBody.put("metadata", Map.of("email", email, "categories", categories));
        requestBody.put("namespace","namespace1");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        //ResponseEntity<String> response = restTemplateConfig.restTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);
        ResponseEntity<String> response = restTemplate.exchange(apiURL,HttpMethod.POST,requestEntity,String.class);



        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Veri başarıyla kaydedildi.");
        } else {
            System.err.println("Veri kaydedilirken hata oluştu: " + response.getBody());
        }

    }




}
