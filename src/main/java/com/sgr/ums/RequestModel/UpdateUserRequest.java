package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest {


        @NotBlank(message = "Id is required")
        private Long id;

        @NotBlank(message = "FirstName is required")
        private String firstName;

        private String middleName;

        @NotBlank(message = "LastName is required")
        private String lastName;

        @NotBlank(message = "NativeName is required")
        private String nativeName;

        @NotBlank(message = "Age is required")
        private Integer age;

        @NotBlank(message="Address is required")
        private String address;



        //getter and Setter

        public @NotBlank(message = "Address is required") String getAddress() {
            return address;
        }

        public void setAddress(@NotBlank(message = "Address is required") String address) {
            this.address = address;
        }

        public @NotBlank(message = "Age is required") Integer getAge() {
            return age;
        }

        public void setAge(@NotBlank(message = "Age is required") Integer age) {
            this.age = age;
        }

        public @NotBlank(message = "Country is required") String getCountry() {
            return country;
        }

        public void setCountry(@NotBlank(message = "Country is required") String country) {
            this.country = country;
        }

        public @NotBlank(message = "FirstName is required") String getFirstName() {
            return firstName;
        }

        public void setFirstName(@NotBlank(message = "FirstName is required") String firstName) {
            this.firstName = firstName;
        }

        public @NotBlank(message = "Id is required") Long getId() {
            return id;
        }

        public void setId(@NotBlank(message = "Id is required") Long id) {
            this.id = id;
        }

        public @NotBlank(message = "LastName is required") String getLastName() {
            return lastName;
        }

        public void setLastName(@NotBlank(message = "LastName is required") String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public @NotBlank(message = "NativeName is required") String getNativeName() {
            return nativeName;
        }

        public void setNativeName(@NotBlank(message = "NativeName is required") String nativeName) {
            this.nativeName = nativeName;
        }

        public @NotBlank(message = "PhoneNumber is required") String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(@NotBlank(message = "PhoneNumber is required") String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @NotBlank(message = "PhoneNumber is required")
        private String phoneNumber;

        @NotBlank(message = "Country is required")
        private String country;





        public UpdateUserRequest(){}
        public UpdateUserRequest( Long id,String firstName,String middleName,String lastName,String nativeName,Integer age,String address,String phoneNumber,String country) {
            this.id = id;

            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.nativeName = nativeName;
            this.age = age;
            this.address=address;
            this.phoneNumber = phoneNumber;
            this.country = country;


        }




        @Override
        public String toString(){
            return "UpdateUserRequest("+
                    ",id='"+id+'\''+
                    ",firstName='"+firstName+'\''+
                    ",middleName='"+middleName+'\''+
                    ",lastName='"+lastName+'\''+
                    ",nativeName='"+nativeName+'\''+
                    ",age='"+age+'\''+
                    ",address='"+address+'\''+
                    ",phoneNumber='"+phoneNumber+'\''+
                    ",country='"+country+'\''+
                    ')';
        }

    }


