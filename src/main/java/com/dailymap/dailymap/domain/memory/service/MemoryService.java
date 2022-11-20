package com.dailymap.dailymap.domain.memory.service;

import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.domain.memory.repository.MemoryRepository;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMORY_NOT_EXISTS_BY_ID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public Memory save(final Memory memory) {
        return memoryRepository.save(memory);
    }

    public Memory findById(final Long id) {
        return memoryRepository.findById(id)
            .orElseThrow(() -> new BusinessException(MEMORY_NOT_EXISTS_BY_ID));
    }

}
