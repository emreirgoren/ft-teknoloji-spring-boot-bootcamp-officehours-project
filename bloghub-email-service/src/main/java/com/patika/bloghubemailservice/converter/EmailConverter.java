package com.patika.bloghubemailservice.converter;

import com.patika.bloghubemailservice.dto.request.EmailCreateRequest;
import com.patika.bloghubemailservice.model.Email;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailConverter {

    public static Email toEntity(EmailCreateRequest request, String templateContent) {
        return new Email(request.getTo(), request.getEmailTemplate(), templateContent, LocalDateTime.now());
    }
}
