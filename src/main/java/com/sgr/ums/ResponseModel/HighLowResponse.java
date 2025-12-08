package com.sgr.ums.ResponseModel;

public record HighLowResponse(
        Double  weeks_high_52,
        Double  weeks_low_52,
        Double  days_avg_120,
        Double  days_avg_180,
        Double  all_time_high_volume,
        Double  weeks_high_volume_52,
        Double  weeks_low_volume_52,
        Double  days_avg_volume_50

) {}

//{
//    "weeks_high_52": 6630.0,
//    "weeks_low_52": 4566.1,
//    "days_avg_120": 5056.03,
//    "days_avg_180": 5228.82,
//    "all_time_high_volume": 14688.0,
//    "weeks_high_volume_52": 9596.0,
//    "weeks_low_volume_52": 70.0,
//    "days_avg_volume_50": 580.97
//}