package com.weykko.librarysystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public String devProfile() {
        log.info("Dev profile started");
        return "";
    }
}
