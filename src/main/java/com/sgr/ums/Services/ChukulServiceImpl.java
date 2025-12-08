package com.sgr.ums.Services;

import com.sgr.ums.ResponseModel.HighLowResponse;
import com.sgr.ums.ResponseModel.HistoryDataResponse;
import org.springframework.stereotype.Service;

    @Service
    public class ChukulServiceImpl implements ChukulService {

        private final ChukulApiClient apiClient;

        public ChukulServiceImpl(ChukulApiClient apiClient) {
            this.apiClient = apiClient;
        }

        @Override
        public HighLowResponse fetchHighLow(String symbol) throws Exception {
            return apiClient.getHighLow(symbol);
        }

        @Override
        public HistoryDataResponse[] fetchHistoryData(String symbol) throws Exception {
            return apiClient.getHistoryData(symbol);
        }
    }


