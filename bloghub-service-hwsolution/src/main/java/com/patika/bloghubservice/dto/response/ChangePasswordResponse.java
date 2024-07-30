package com.patika.bloghubservice.dto.response;

import lombok.Builder;

@Builder
public record ChangePasswordResponse(

        String email,
        String message

) {



}
