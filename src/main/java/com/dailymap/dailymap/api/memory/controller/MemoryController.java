package com.dailymap.dailymap.api.memory.controller;

import com.dailymap.dailymap.api.memory.dto.MemoryRequestDto;
import com.dailymap.dailymap.api.memory.dto.MemoryResponseDto;
import com.dailymap.dailymap.api.memory.service.MemoryApiService;

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
public class MemoryController {

    private final MemoryApiService memoryService;

    @PostMapping
    public ResponseEntity<MemoryResponseDto.Register> savedMemory(
        @RequestHeader("Authorization")String authorization,
        @RequestBody MemoryRequestDto.Register requestDto
    ) {
        MemoryResponseDto.Register responseDto = memoryService.save(authorization, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<MemoryResponseDto.Find> findMemory(
        @RequestHeader("Authorization") String authorization,
        @PathVariable Long id
    ) {
        MemoryResponseDto.Find responseDto = memoryService.getResponseDto(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<MemoryResponseDto.Find>> findAllMemory(
        @RequestHeader("Authorization") String authorization,
        @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<MemoryResponseDto.Find> responseDtos = memoryService.getResponseDtos(authorization, pageable);
        return ResponseEntity.ok(responseDtos);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MemoryResponseDto.Update> changeContent(
        @RequestHeader("Authorization") String authorization,
        @PathVariable final Long id,
        @RequestBody MemoryRequestDto.Update requestDto
    ) {
        MemoryResponseDto.Update responseDto = memoryService.update(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMemory(@PathVariable final Long id) {
        return ResponseEntity.ok(memoryService.delete(id));
    }

}
