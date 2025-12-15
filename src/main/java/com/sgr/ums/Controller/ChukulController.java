package com.sgr.ums.Controller;

import com.sgr.ums.ResponseModel.HighLowResponse;
import com.sgr.ums.ResponseModel.HistoryDataResponse;
import com.sgr.ums.Services.ChukulService.ChukulService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
public class ChukulController {

    private final ChukulService service;

    public ChukulController(ChukulService service) {
        this.service = service;
    }

    @GetMapping("/high-low")
    public HighLowResponse getHighLow(@RequestParam String symbol) throws Exception {
        return service.fetchHighLow(symbol);
    }

    @GetMapping("/history")
    public HistoryDataResponse[] getHistory(@RequestParam String symbol) throws Exception {
        return service.fetchHistoryData(symbol);
    }
}