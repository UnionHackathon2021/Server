package com.example.demo.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
        redisUtil.deleteData("Redis");
        redisUtil.setData("Redis", "Redis-test");
        Assertions.assertThat(redisUtil.getData("Redis")).isEqualTo("Redis-test");
    }
}