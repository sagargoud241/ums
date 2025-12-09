package com.sgr.ums.Controller;

import com.sgr.ums.RequestModel.ThirdPartyRequestModel.AddThirdPartyRequest;
import com.sgr.ums.RequestModel.ThirdPartyRequestModel.IdListRequest;
import com.sgr.ums.RequestModel.ThirdPartyRequestModel.PatchThirdPartyRequest;
import com.sgr.ums.RequestModel.ThirdPartyRequestModel.UpdateThirdPartyRequest;
import com.sgr.ums.ResponseModel.ThirdPartyApiResponse;
import com.sgr.ums.Services.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/thirdParty")
public class ThirdPartyController {
    private final ThirdPartyService thirdPartyService;

    @Autowired
    public ThirdPartyController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping("/getAllObject")
    public ThirdPartyApiResponse getAllProducts() {
        return thirdPartyService.listAllObject();
    }

    @GetMapping("/{id}")
    public ThirdPartyApiResponse getObjectById(Long id) {
        return thirdPartyService.getObjectById(id);
    }

    @GetMapping("/getInList/{ids}")
    public ThirdPartyApiResponse getObjectByIds(@PathVariable List<Integer> ids) {
        return thirdPartyService.getObjectByIds(ids);
    }

    @PostMapping("/getInList")
    public ThirdPartyApiResponse getObjectByIds(@RequestBody IdListRequest ids) {
        return thirdPartyService.getObjectByIds(ids.getIds());
    }

    @PostMapping("/addNewObject")
    public ThirdPartyApiResponse addNewObject(@RequestBody AddThirdPartyRequest request) {
        return thirdPartyService.addNewObject(request);
    }

    @PutMapping("/update/{id}")
    public ThirdPartyApiResponse updateObjectById(@PathVariable Long id, @RequestBody UpdateThirdPartyRequest request) {
        return thirdPartyService.updateObjectById(id, request);
    }

    @PatchMapping("/update/{id}")
    public ThirdPartyApiResponse patchObjectById(@PathVariable Long id, @RequestBody PatchThirdPartyRequest request) {
        return thirdPartyService.patchObjectById(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ThirdPartyApiResponse deleteObjectById(Long id) {
        return thirdPartyService.deleteObjectById(id);
    }

}
