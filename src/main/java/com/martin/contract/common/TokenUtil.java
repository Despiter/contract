package com.martin.contract.common;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    // 生成Token并存入Redis（有效期10分钟）
    public String generateToken(String userName) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("USER_TOKEN:" + userName, token, 10, TimeUnit.MINUTES);
        return token;
    }

    // 验证Token是否有效
    public boolean validateToken(String userName, String token) {
        String storedToken = redisTemplate.opsForValue().get("USER_TOKEN:" + userName);
        return token != null && token.equals(storedToken);
    }

    // 删除Token（退出登录）
    public void deleteToken(String userName) {
        redisTemplate.delete("USER_TOKEN:" + userName);
    }
}