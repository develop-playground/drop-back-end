package com.dailymap.dailymap.domain.memory.service;

import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.domain.memory.repository.MemoryRepository;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMORY_NOT_EXISTS_BY_ID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public Memory save(final Memory memory) {
        return memoryRepository.save(memory);
    }

    public List<Memory> findAllByMember(Member member, Pageable pageable) {
        return memoryRepository.findAllByMember(member, pageable);
    }

    public Memory findById(final Long id) {
        return memoryRepository.findById(id)
            .orElseThrow(() -> new BusinessException(MEMORY_NOT_EXISTS_BY_ID));
    }

    @Transactional
    public void delete(final Memory memory) {
        memoryRepository.delete(memory);
    }
}
