package com.sgr.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // Enables scheduled background jobs
public class UmsApplication {
   // private static final Logger log = LoggerFactory.getLogger(UmsApplication.class);
	public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class, args);
     //   log.info("Application started");
        //log.warn("Something might be wrong");
        //log.error("Something failed!", new RuntimeException("Test error"));
	}

}
