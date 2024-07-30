package com.patika.bloghubemailservice.repository;

import com.patika.bloghubemailservice.model.enums.EmailTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmailTemplateRepository {

    private Map<EmailTemplate, String> emailTemplateStringMap = new HashMap<>();

    public String findTemplate(EmailTemplate emailTemplate) {
        return emailTemplateStringMap.get(emailTemplate);
    }

    public EmailTemplate save(EmailTemplate emailTemplate, String templateContent) {
        emailTemplateStringMap.put(emailTemplate, templateContent);
        return emailTemplate;
    }
}
