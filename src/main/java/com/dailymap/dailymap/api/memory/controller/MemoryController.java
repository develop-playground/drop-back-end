package com.dailymap.dailymap.api.memory.controller;

import com.dailymap.dailymap.api.memory.dto.MemoryRequestDto;
import com.dailymap.dailymap.api.memory.dto.MemoryResponseDto;
import com.dailymap.dailymap.api.memory.service.MemoryApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memory")
@Tag(name = "추억", description = "추억 API")
public class MemoryController {

    private final MemoryApiService memoryService;

    @Operation(summary = "추억 등록", description = "추억을 DROP 하도록 해주는 등록 API")
    @PostMapping
    public ResponseEntity<MemoryResponseDto.Register> savedMemory(
        @RequestHeader("Authorization")String authorization,
        @RequestBody MemoryRequestDto.Register requestDto
    ) {
        MemoryResponseDto.Register responseDto = memoryService.save(authorization, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "추억 상세조회", description = "하나의 추억 데이터를 제공하는 상세조회 API", hidden = true)
    @GetMapping("{id}")
    public ResponseEntity<MemoryResponseDto.Find> findMemory(@PathVariable Long id) {
        MemoryResponseDto.Find responseDto = memoryService.getResponseDto(id);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "추억 목록 조회", description = "로그인 한 사용자의 추억 목록을 제공하는 API")
    @GetMapping
    public ResponseEntity<List<MemoryResponseDto.Find>> findAllMemory(
        @RequestHeader("Authorization") String authorization,
        @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<MemoryResponseDto.Find> responseDtos = memoryService.getResponseDtos(authorization, pageable);
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(summary = "추억 수정", description = "추억의 content 내용을 수정하는 API")
    @PatchMapping("{id}")
    public ResponseEntity<MemoryResponseDto.Update> changeContent(
        @RequestHeader("Authorization") String authorization,
        @PathVariable final Long id,
        @RequestBody MemoryRequestDto.Update requestDto
    ) {
        MemoryResponseDto.Update responseDto = memoryService.update(authorization, id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "추억 삭제", description = "추억을 삭제하는 API")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMemory(
        @RequestHeader("Authorization") String authorization,
        @PathVariable final Long id
    ) {
        return ResponseEntity.ok(memoryService.delete(authorization,id));
    }

}
