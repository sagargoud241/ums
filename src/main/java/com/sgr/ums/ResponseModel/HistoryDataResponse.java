package com.sgr.ums.ResponseModel;

public record HistoryDataResponse(
        String date,
        String symbol,
        Double  open,
        Double  close,
        Double  high,
        Double  low,
        Long volume,
        Double ltp,
        Double amount
) {}


//{"date":"2025-12-03","symbol":"BBC","open":4985.0,"high":4985.0,"low":4840.3,"close":4924.9,"ltp":4924.9,"volume":599.0,"amount":2920998.0}