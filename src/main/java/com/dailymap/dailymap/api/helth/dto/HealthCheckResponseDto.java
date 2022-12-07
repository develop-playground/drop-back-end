package com.dailymap.dailymap.api.helth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "헬스체크 응답")
public class HealthCheckResponseDto {

    private boolean status;

    private String health;

}
