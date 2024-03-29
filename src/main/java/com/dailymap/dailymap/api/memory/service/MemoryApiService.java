package com.dailymap.dailymap.api.memory.service;

import com.dailymap.dailymap.api.memory.dto.MemoryRequestDto;
import com.dailymap.dailymap.api.memory.dto.MemoryResponseDto;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.domain.memory.service.MemoryService;
import com.dailymap.dailymap.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMBER_NOY_EXISTS_BY_ACCESS_TOKEN;
import static com.dailymap.dailymap.global.error.exception.ErrorCode.NOT_VALID_MEMORY_BY_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoryApiService {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    private final MemoryService memoryService;

    private final String DELETE_SUCCESS_MESSAGE = "success";

    @Transactional
    public MemoryResponseDto.Register save(final String authorization, final MemoryRequestDto.Register requestDto) {
        String email = getMemberEmail(authorization);
        Member findMember = getMemberByEmail(email);

        Memory memory = requestDto.toEntity(findMember);
        Memory savedMemory = memoryService.save(memory);

        return MemoryResponseDto.Register.from(savedMemory);
    }

    public MemoryResponseDto.Find getResponseDto(final Long id) {
        Memory memory = memoryService.findById(id);
        return MemoryResponseDto.Find.from(memory);
    }

    public List<MemoryResponseDto.Find> getResponseDtos(final String authorization, final Pageable pageable) {
        String email = getMemberEmail(authorization);
        Member findMember = getMemberByEmail(email);

        // todo : QueryDSL 사용하여 페이징 반영한 DTO List 반환되도록 리팩터링 시도하기
        List<Memory> memories = memoryService.findAllByMember(findMember, pageable);

        return memories.stream()
            .map(MemoryResponseDto.Find::from)
            .collect(Collectors.toList());
    }

    private Member getMemberByEmail(final String email) {
        return memberService.findMemberByEmail(email)
            .orElseThrow(() -> new BusinessException(MEMBER_NOY_EXISTS_BY_ACCESS_TOKEN));
    }

    private String getMemberEmail(final String authorization) {
        String accessToken = authorization.split(" ")[1];
        return tokenManager.getMemberEmail(accessToken);
    }

    @Transactional
    public MemoryResponseDto.Update update(
        final String authorization,
        final Long id,
        final MemoryRequestDto.Update requestDto
    ) {
        String newContent = requestDto.getContent();

        Memory findMemory = memoryService.findById(id);
        validateMemory(authorization, findMemory);

        findMemory.updateContent(newContent);
        return MemoryResponseDto.Update.from(findMemory);
    }

    @Transactional
    public String delete(final String authorization,final Long id) {
        Memory findMemory = memoryService.findById(id);
        validateMemory(authorization, findMemory);

        memoryService.delete(findMemory);
        return DELETE_SUCCESS_MESSAGE;
    }

    private void validateMemory(String authorization, Memory memory) {
        String email = getMemberEmail(authorization);
        Member findMember = memory.getMember();

        if (!findMember.isSameEmail(email)) {
            throw new BusinessException(NOT_VALID_MEMORY_BY_MEMBER);
        }
    }

}
