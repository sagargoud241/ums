package com.sgr.ums.Jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleBackgroundJob {

    // Run every 10 seconds
//    @Scheduled(fixedRate = 10000)
//    public void runJob() {
//        System.out.println("Run every 10 seconds Background Job Executed at: " + System.currentTimeMillis());
//    }
//
//    // Example Cron Job (every 30 seconds)
//    @Scheduled(cron = "0/10 * * * * *")
//    public void cronJob() {
//        System.out.println("Example Cron Job (every 10 seconds) Cron Job executed at: " + System.currentTimeMillis());
//    }
//
//    // 🔵 NEW: Run every 20 seconds
//    @Scheduled(cron = "0/20 * * * * *")
//    public void runEvery20Seconds() {
//        System.out.println("Run every 20 seconds Job executed at: " + System.currentTimeMillis());
//    }

    // 🔵 NEW: Run every 30 seconds (another method)
//    @Scheduled(cron = "0/30 * * * * *")
//    public void runAnother30SecondJob() {
//        System.out.println("Another 30-second Job executed at: " + System.currentTimeMillis());
//    }

    // 🔵 NEW: Run every 1 hour
    // Runs at minute 0, second 0 of every hour
    @Scheduled(cron = "0 0 * * * *")
    public void runEveryHour() {
        System.out.println("Run every 1 hour Job executed at: " + System.currentTimeMillis());
    }

    // 1️⃣ Every 5 hours (CRON)
    // ----------------------------------------
    @Scheduled(cron = "0 0 */5 * * *")
    public void runEvery5Hours() {
        System.out.println("Runs every 5 hours → " + System.currentTimeMillis());
    }

    // ----------------------------------------
    // 2️⃣ After every 2 days (Fixed Rate OR Cron Option)
    // BEST: Use fixedRate for "every 2 days"

    @Scheduled(fixedRate = 172800000L)
    // 2 days = 2 × 24 × 60 × 60 × 1000 = 172,800,000 ms
    public void runEvery2Days() {
        System.out.println("Runs every 2 days → " + System.currentTimeMillis());
    }


    // 3️⃣ After every 1 week (7 days)
    // Cron CANNOT do "every X weeks" → use fixedRate
    @Scheduled(fixedRate = 604800000L)
    // 1 week = 7 days = 7 × 24 × 60 × 60 × 1000
    public void runEvery1Week() {
        System.out.println("Runs every 1 week → " + System.currentTimeMillis());
    }


    // 4️⃣ Every 3 months
    // Cron needs specific months (quarterly)
    // ----------------------------------------
    @Scheduled(cron = "0 0 0 1 1,4,7,10 *")
    public void runEvery3Months() {
        System.out.println("Runs every 3 months (quarterly) → " + System.currentTimeMillis());
    }

    // 5️⃣ Every 9 months
    // Cron needs specific months
    @Scheduled(cron = "0 0 0 1 1,10 *")
    public void runEvery9Months() {
        System.out.println("Runs every 9 months → " + System.currentTimeMillis());
    }


}

