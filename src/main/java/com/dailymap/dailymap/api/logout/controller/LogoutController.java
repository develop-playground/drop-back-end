package com.dailymap.dailymap.api.logout.controller;


import com.dailymap.dailymap.api.logout.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LogoutController {

	private final LogoutService logoutService;

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization) {
		return ResponseEntity.ok(logoutService.logout(authorization));
	}

}
