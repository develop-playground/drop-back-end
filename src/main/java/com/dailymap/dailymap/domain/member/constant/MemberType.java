package com.dailymap.dailymap.domain.member.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

	KAKAO(0, "카카오회원"),
	NAVER(1, "네이버회원"),
	GOOGLE(2, "구글회원"),
	;

	private Integer id;
	private String title;

}
