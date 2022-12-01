package com.dailymap.dailymap.api.helth.controller;

import com.dailymap.dailymap.api.helth.dto.HealthCheckResponseDto;
import com.dailymap.dailymap.api.helth.service.HealthCheckService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health")
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @Operation(summary = "헬스 체크 API", description = "API 서버의 구동 여부를 확인합니다.", tags = "헬스 체크")
    @GetMapping
    public ResponseEntity<HealthCheckResponseDto> healthCheck() {
        HealthCheckResponseDto responseDto = healthCheckService.createResponse();
        return ResponseEntity.ok(responseDto);
    }

}
