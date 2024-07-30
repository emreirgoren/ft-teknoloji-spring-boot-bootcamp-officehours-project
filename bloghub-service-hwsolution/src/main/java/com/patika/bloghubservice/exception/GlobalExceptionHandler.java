package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.dto.response.GenericResponseConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogHubException.class)
    public GenericResponse handleBlogHubException(BlogHubException blogHubException) {
        return GenericResponse.failed(blogHubException.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse handleEmailNotFoundException(EmailNotFoundException emailNotFoundException) {

        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Kullanıcı Bulunamadı")
                .status(GenericResponseConstants.NOT_FOUND)
                .build();
        return genericResponse;
    }

    @ExceptionHandler(PasswordWrongException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericResponse handlePasswordWrongException(PasswordWrongException passwordWrongException) {

        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Şifre Yanlış.")
                .exceptionMessage(passwordWrongException.getMessage())
                .status(GenericResponseConstants.WRONG_PASSWORD)
                .build();
        return genericResponse;
    }

    @ExceptionHandler(PasswordLengthException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public GenericResponse handlePasswordLengthException(PasswordLengthException passwordLengthException) {
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                .message("Şifre çok uzun yada cok kısa")
                .exceptionMessage(passwordLengthException.getMessage())
                .status(GenericResponseConstants.PASSWORD_UNPROCESSABLE_ENTITY)
                .build();
        return genericResponse;
    }

    @ExceptionHandler(LikeLimitExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericResponse handleLikeLimitExceededException(LikeLimitExceededException likeLimitExceededException) {
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.CONFLICT)
                .message("Beğeni sınırına ulaşıldı")
                .exceptionMessage(likeLimitExceededException.getMessage())
                .status(GenericResponseConstants.LİKE_EXCEEDED)
                .build();
        return genericResponse;
    }

    @ExceptionHandler(ChangeBlogStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse handleChangeBlogStatusException(ChangeBlogStatusException changeBlogStatusException) {
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Status Published olan statüsü değiştirelemez")
                .exceptionMessage(changeBlogStatusException.getMessage())
                .status(GenericResponseConstants.FAILED)
                .build();
        return genericResponse;
    }

    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse handleBlogNotFoundException(BlogNotFoundException blogNotFoundException) {
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("BLog bulunamadı.")
                .status(GenericResponseConstants.NOT_FOUND)
                .exceptionMessage(blogNotFoundException.getMessage())
                .build();
        return genericResponse;
    }

    @ExceptionHandler(DraftStatusBlogNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse handleDraftStatusBlogNotFound(DraftStatusBlogNotFound draftStatusBlogNotFound) {
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Draft blog bulunamadı.")
                .status(GenericResponseConstants.NOT_FOUND)
                .exceptionMessage(draftStatusBlogNotFound.getMessage())
                .build();
        return genericResponse;
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericResponse handleUnauthorizedUserException(UnauthorizedUserException unauthorizedUserException){
        GenericResponse genericResponse = GenericResponse.builder()
                .data(null)
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Sadece onaylanmıs kullanıcılar blog yayınlayabilir.")
                .status(GenericResponseConstants.UNAUTHORIZED_USER_STATUS)
                .exceptionMessage(unauthorizedUserException.getMessage())
                .build();
        return genericResponse;
    }

}
