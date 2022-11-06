package com.dailymap.dailymap.api.memberinfo.dto;

import com.dailymap.dailymap.domain.member.model.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {

    private Long id;

    private String email;

    private String username;

    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .username(member.getUsername())
            .build();
    }

}
