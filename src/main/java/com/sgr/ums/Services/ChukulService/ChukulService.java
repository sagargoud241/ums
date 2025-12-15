package com.sgr.ums.Services.ChukulService;

import com.sgr.ums.ResponseModel.HighLowResponse;
import com.sgr.ums.ResponseModel.HistoryDataResponse;

public interface ChukulService {


        HighLowResponse fetchHighLow(String symbol) throws Exception;

        HistoryDataResponse[] fetchHistoryData(String symbol) throws Exception;
    }


