package com.patika.bloghubemailservice.service;

import com.patika.bloghubemailservice.converter.EmailConverter;
import com.patika.bloghubemailservice.dto.request.EmailCreateRequest;
import com.patika.bloghubemailservice.exception.EmailNotFoundException;
import com.patika.bloghubemailservice.model.Email;
import com.patika.bloghubemailservice.repository.EmailRepository;
import com.patika.bloghubemailservice.repository.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmailTemplateRepository emailTemplateRepository;

    public void sendEmail(EmailCreateRequest request) {

        try {
            String templateContent = emailTemplateRepository.findTemplate(request.getEmailTemplate());

            Email email = EmailConverter.toEntity(request, templateContent);

            emailRepository.save(email);

        }catch (Exception e){
            throw new EmailNotFoundException();
        }
        log.info("email gönderildil. request: {}", request);
    }
}
