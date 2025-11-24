package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class LoginUserRequest {

        @NotBlank(message = "User Name is required")
        private String userName;

        @NotBlank(message = "Password is required")
        private String password;

        public @NotBlank(message = "User Name is required") String getUserName() {
            return userName;
        }

        public void setUserName(@NotBlank(message = "User Name is required") String userName) {
            this.userName = userName;
        }

        public @NotBlank(message = "Password is required") String getPassword() {
            return password;
        }

        public void setPassword(@NotBlank(message = "Password is required") String password) {
            this.password = password;
        }



}
