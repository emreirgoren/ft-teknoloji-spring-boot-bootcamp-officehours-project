package com.patika.bloghubemailservice.model;

import com.patika.bloghubemailservice.model.enums.EmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Email {

    private String to;
    private EmailTemplate emailTemplate;
    private String emailContent;
    private LocalDateTime createdDateTime;
}
