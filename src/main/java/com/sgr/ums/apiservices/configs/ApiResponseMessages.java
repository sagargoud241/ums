package com.sgr.ums.apiservices.configs;

public enum ApiResponseMessages {

    API_MIGRATED("Please contact to the administration."),

    SUCCESS("SUCCESS"), FAILURE("FAILURE"),
    // OTP
    PHONE_ADDED_CHECK_OTP("The phone number has been added. Please check for OTP message."),
    PHONE_CHECK_OTP("Please check for OTP message being sent to your phone number."),

    // passwords
    PASSWORD_DIDNOT_MATCH("Password did not match. Please try again."),
    PASSWORD_SET_SUCCESSFULLY("Password set successfully."),
    PASSWORD_RESET_LINK_SENT("A password reset link has been sent to your email. Please check the inbox."),

    // Messages
    CREATED_SUCCESSFULLY("Created successfully."), CREATION_FAILED("Creation Failed."),
    UPDATED_SUCCESSFULLY("Updated successfully."), UPDATE_FAILED("Update Failed."),
    UPLOADED_SUCCESSFULLY("Uploaded successfully."), FAILED_TO_UPLOAD_THE_FILE("Failed to upload the file."),
    DELETED_SUCCESSFULLY("Deleted successfully."), DELETION_FAILED("Deletion Failed."),
    DATA_RETRIEVED_SUCCESSFULLY("Data retrieved successfully."),
    NO_RESULT_FOUND("No result found for your requested query."),

    LOGIN_RESTRICTION("The access to your accounts has been temporarily restricted."),
    LOGIN_FAILED("Invalid credentials."), LOGIN_SUCCESSFULLY("Welcome back to our GME community."),

    UNAUTHORIZED_ACCESS("You are not authorized to make this request"),

    INSUFFICIENT_INFORMATION("Insufficient Information"), INVALID_FUNCTION_CODE_OR_SCOPE("Invalid Function !"),

    DUPLICATE_DATA("Duplicate data!"), REMOVED_SUCCESSFULLY("REMOVED_SUCCESSFULLY"),

    // exceptation cases
    REQUEST_TERMINATED("Request Terminated."),

    // data not available
    NOT_AVAILABLE("Not available."), VALIDATION_FAILED("Invalid Process!"),
    INVALID_API_REQUEST_DATA("Invalid API request data."),
    // apiLog
    DATE_RANGE_REQUIRED("Date Range is Required."), DUPLICATE_ARTICLE("Duplicate Article Text Value"),
    ARTICLE_TEXT_UNIQUE("Article Text must be unique and not null."), ENTITY_NOT_NULL("Entity must not null."),
    ENTITY_TYPE_NOT_NULL("Entity Type must not null."), ARTICLE_VALUE_NOT_NULL("Article value must not null."),
    ENTITY_NOT_FOUND("No Entity Found."),















    ;

    private final String name;

    ApiResponseMessages(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}