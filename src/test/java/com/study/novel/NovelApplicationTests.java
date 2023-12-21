package com.study.novel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;

@SpringBootTest
class NovelApplicationTests {

    @Test
    @Cacheable
    public int contextLoads() {
        return 1;
    }

}
