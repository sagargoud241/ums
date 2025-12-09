package com.sgr.ums.ResponseModel;

import lombok.Data;

@Data
public class ThirdPartyApiResponse {
    private String code;
    private String message;
    private Object dto;
}
