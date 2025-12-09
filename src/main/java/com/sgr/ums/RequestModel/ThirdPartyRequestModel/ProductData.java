package com.sgr.ums.RequestModel.ThirdPartyRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductData {

    private Integer year;

    private Double price;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;

   // private String color;
}

////update
//private int year;
//private double price;
//
//@JsonProperty("CPU model")
//private String cpuModel;
//
//@JsonProperty("Hard disk size")
//private String hardDiskSize;
//
//private String color;