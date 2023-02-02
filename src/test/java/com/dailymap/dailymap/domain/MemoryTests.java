package com.dailymap.dailymap.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MemoryTests {

    @Test
    @DisplayName("블로그용 테스트")
    public void test() {
        Assertions.assertEquals(1,0);
    }

}
