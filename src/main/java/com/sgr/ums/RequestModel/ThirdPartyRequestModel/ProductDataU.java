package com.sgr.ums.RequestModel.ThirdPartyRequestModel;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDataU {

    private Integer year;

    private Double price;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;

    private String color;
}