package com.dailymap.dailymap.api.helth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCheckResponseDto {

    private boolean status;

    private String health;

}
