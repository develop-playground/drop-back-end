package com.dailymap.dailymap.domain.memory.repository;

import com.dailymap.dailymap.domain.memory.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
}
