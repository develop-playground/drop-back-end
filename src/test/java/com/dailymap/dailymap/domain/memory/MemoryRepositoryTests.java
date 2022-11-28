package com.dailymap.dailymap.domain.memory;

import com.dailymap.dailymap.domain.memory.model.Location;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.domain.memory.repository.MemoryRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class MemoryRepositoryTests {

    @Autowired
    private MemoryRepository memoryRepository;

    @Test
    @DisplayName("추억 생성 성공 검증 테스트")
    @Transactional
    public void createMemorySuccessTest() {
        // Arrange
        Location location = Location.builder()
            .latitude(111.333)
            .longitude(222.333)
            .build();

        Memory memory = Memory.builder()
            .content("테스트 문구 입니다.")
            .imageUrls(List.of("url_01.jpg","url_02.jpg"))
            .location(location)
            .build();

        // Act
        Memory savedMemory = memoryRepository.save(memory);

        // Assert
        assertEquals(1L, savedMemory.getId());
        assertEquals("테스트 문구 입니다.", savedMemory.getContent());
    }

    // ToDo : 조회, 수정, 삭제 테스트 코드 작성

}
