package com.dailymap.dailymap.domain.memory.repository;

import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.memory.model.Memory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findAllByMember(final Member member, Pageable pageable);
}
