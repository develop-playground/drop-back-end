package com.dailymap.dailymap.api.helth.service;

import com.dailymap.dailymap.api.helth.dto.HealthCheckResponseDto;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {


	public HealthCheckResponseDto createResponse() {
		return HealthCheckResponseDto.builder()
			.status(true)
			.health("ok")
			.build();
	}
}
