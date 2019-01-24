package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.post.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;

@Configuration
public class Caches {

    @Bean
    public Cache<Long, Post> postCache(CacheManager cacheManager) {

        return cacheManager.getCache("postCache", Long.class, Post.class);
    }

}
