package com.patika.bloghubservice.dto.request;

public record ChangePassRequest(
        String existingEmail,
        String existingPassword,
        String changedPassword
) {
}
