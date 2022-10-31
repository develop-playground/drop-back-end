package com.dailymap.dailymap.api.login.dto;


import com.dailymap.dailymap.domain.member.constant.MemberType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    private MemberType memberType;

}
