package com.sgr.ums.RequestModel.ThirdPartyRequestModel;

import lombok.Data;

import java.util.List;
import java.util.Optional;


@Data
public class IdListRequest {
    private List<Integer> ids;
}
