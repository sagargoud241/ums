package com.sgr.ums.ResponseModel;

public class ApiResponse <T>{
    private String code;
    private String message;
    private T data;

    // --- Constructors ---
    public ApiResponse() {
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    // --- Getters ---
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // --- Setters ---
    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }


    // --- Static factory methods ---
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>("fail", message, null);
    }

    public static <T> ApiResponse<T> exception(String message) {
        return new ApiResponse<>("errror", message,null);
    }


    // --- Builder Pattern ---
    public static class Builder<T> {

        private String code;
        private String message;
        private T data;

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(code, message, data);
        }
    }


    // --- ToString ---
    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
