package com.patika.bloghubemailservice.service;

import com.patika.bloghubemailservice.model.enums.EmailTemplate;
import com.patika.bloghubemailservice.repository.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate createTemplate(EmailTemplate emailTemplate, String templateContent) {

        return emailTemplateRepository.save(emailTemplate, templateContent);
    }
}
