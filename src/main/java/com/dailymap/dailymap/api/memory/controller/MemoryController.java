package com.dailymap.dailymap.api.memory.controller;

import com.dailymap.dailymap.api.memory.dto.MemoryRequestDto;
import com.dailymap.dailymap.api.memory.dto.MemoryResponseDto;

import com.dailymap.dailymap.api.memory.service.MemoryApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memory")
public class MemoryController {

    private final MemoryApiService memoryService;

    @PostMapping
    public ResponseEntity<MemoryResponseDto> savedMemory(
        @RequestHeader("Authorization")String authorization,
        @RequestBody MemoryRequestDto requestDto
    ) {
        MemoryResponseDto responseDto = memoryService.save(authorization, requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
