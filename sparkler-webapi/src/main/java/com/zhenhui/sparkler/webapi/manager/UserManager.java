package com.zhenhui.sparkler.webapi.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhenhui.demo.uic.api.domain.UserDto;
import com.zhenhui.demo.uic.api.service.UserQueryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@CacheConfig(cacheNames = "user-dto")
public class UserManager {

    @Reference(version = "1.0.0")
    private UserQueryService userQueryService;

    @Cacheable(key = "#userId", unless = "#result==null")
    public UserDto getUser(long userId) {
        return userQueryService.queryUser(userId);
    }

    @Cacheable(keyGenerator = "userIdsKeyGenerator", unless = "#userIds==null || #userIds.size() == 0")
    public Map<Long, UserDto> getUsers(Set<Long> userIds) {

        if (CollectionUtils.isEmpty(userIds)) {
            return new HashMap<>();
        }

        return userQueryService.queryUsers(userIds);
    }

    @Component("userIdsKeyGenerator")
    public static class UserIdsKeyGenerator implements KeyGenerator {

        @Override
        public Object generate(Object obj, Method method, Object... objects) {

            Collection<Long> userIds = (Collection<Long>) objects[0];

            return "s-" + StringUtils.join(userIds, ",");
        }
    }
}


