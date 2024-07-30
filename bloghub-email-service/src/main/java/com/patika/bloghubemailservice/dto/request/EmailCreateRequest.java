package com.patika.bloghubemailservice.dto.request;

import com.patika.bloghubemailservice.model.enums.EmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailCreateRequest {

    private String to;
    private EmailTemplate emailTemplate;
}
