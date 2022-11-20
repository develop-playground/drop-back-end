package com.dailymap.dailymap.api.memory.service;

import com.dailymap.dailymap.api.memory.dto.MemoryRequestDto;
import com.dailymap.dailymap.api.memory.dto.MemoryResponseDto;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.domain.memory.repository.MemoryRepository;
import com.dailymap.dailymap.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMBER_NOY_EXISTS_BY_ACCESS_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryApiService {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    private final MemoryRepository memoryRepository;

    @Transactional
    public MemoryResponseDto.Register save(final String authorization, final MemoryRequestDto.Register requestDto) {
        String email = getMemberEmail(authorization);
        Member findMember = getMemberByEmail(email);

        Memory memory = requestDto.toEntity(findMember);
        Memory savedMemory = memoryRepository.save(memory);

        return MemoryResponseDto.Register.from(savedMemory);
    }

    private Member getMemberByEmail(final String email) {
        return memberService.findMemberByEmail(email)
            .orElseThrow(() -> new BusinessException(MEMBER_NOY_EXISTS_BY_ACCESS_TOKEN));
    }

    private String getMemberEmail(final String authorization) {
        String accessToken = authorization.split(" ")[1];

        return tokenManager.getMemberEmail(accessToken);
    }

}
