package com.patika.bloghubservice.dto.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericResponseConstants {

    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";
    public static final String NOT_FOUND = "Not Found";

    public static final String WRONG_PASSWORD = "401 Unauthorized";
    public static final String PASSWORD_UNPROCESSABLE_ENTITY = "422 Unprocessable Entity";
    public static final String LİKE_EXCEEDED = "409 Conflict";
    public static final String UNAUTHORIZED_USER_STATUS = "401 Unauthorized User Status";
}
