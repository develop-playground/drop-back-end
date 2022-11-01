package com.dailymap.dailymap.global.config.jpa;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

	@NonNull
	@Override
	public Optional<String> getCurrentAuditor() {
		// todo: 어떤 값을 넣을지 결정
		String modifiedBy = "";

		if (!StringUtils.hasText(modifiedBy)) {
			modifiedBy = "SERVER";
		}

		return Optional.of(modifiedBy);
	}
}
