package com.music.musicservice.config;

import com.music.musicservice.service.SecurityService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeignClientConfig implements RequestInterceptor {

    private final SecurityService securityService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            requestTemplate.header("Authorization" + securityService.getUserToken());
        } catch (Exception e) {
            log.error("Feign Client Error", e);
        }
    }
}
