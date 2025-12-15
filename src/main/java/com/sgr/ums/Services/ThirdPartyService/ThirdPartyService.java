package com.sgr.ums.Services.ThirdPartyService;

import com.sgr.ums.RequestModel.ThirdPartyRequestModel.*;
import com.sgr.ums.ResponseModel.ThirdPartyApiResponse;
import java.util.List;

public interface ThirdPartyService {
    ThirdPartyApiResponse listAllObject();

    ThirdPartyApiResponse getObjectById(Long id);

    ThirdPartyApiResponse addNewObject(AddThirdPartyRequest request);

    ThirdPartyApiResponse getObjectByIds(List<Integer> ids);

    ThirdPartyApiResponse updateObjectById(Long id, UpdateThirdPartyRequest request);

    ThirdPartyApiResponse patchObjectById(Long id, PatchThirdPartyRequest request);

    ThirdPartyApiResponse deleteObjectById(Long id);

}
