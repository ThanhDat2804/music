package com.music.taskscheduler.config;

import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeignClientConfig {


    @Override
    public void apply(RequestTemplate requestTemplate){
        try {
            requestTemplate.header("Authorization", "");
        } catch (Exception e) {
            log.error("Error while setting Authorization header", e);
        }
    }
}
