package com.zhenhui.sparkler.webapi.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhenhui.demo.sparklers.uic.security.SecurityAuthenticationFilter;
import com.zhenhui.demo.sparklers.uic.security.SecurityTokenProducer;
import com.zhenhui.demo.uic.api.service.SecurityBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Reference(version = "1.0.0")
    private SecurityBlacklistService blacklistService;

    @Bean
    public SecurityTokenProducer tokenUtils(@Value("${jwt.issuer}") String issuer
            , @Value("${jwt.ttl}") String expiresInSeconds
            , @Value("${jwt.secret}") String secret) {
        return new SecurityTokenProducer(issuer, expiresInSeconds, secret);
    }

    @Bean
    public SecurityAuthenticationFilter authenticationFilter(@Value("${jwt.http-header}") String authHeader
            , @Autowired SecurityTokenProducer tokenProducer) {
        return new SecurityAuthenticationFilter(authHeader
                , tokenProducer
                , blacklistService);
    }

}
