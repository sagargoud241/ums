package com.sgr.ums.RequestModel.ThirdPartyRequestModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateThirdPartyRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private ProductDataU data;

}

