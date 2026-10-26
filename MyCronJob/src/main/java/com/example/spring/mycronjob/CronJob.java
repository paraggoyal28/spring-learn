package com.example.spring.mycronjob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronJob {

    // Runs every day at 12:00:00 PM
    @Scheduled(cron = "0 0 12 * * ?")
    public void runDailyTask() {
        System.out.println("Daily reported task executed at: " + LocalDateTime.now());
    }

    // Runs every 5 minutes
    @Scheduled(cron = "0 * * * * *")
    public void runEveryMinute() {
        System.out.println("Heartbeat check executed in: " + LocalDateTime.now());
    }
}
