package com.dailymap.dailymap.api.login.dto;


import com.dailymap.dailymap.domain.member.constant.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "로그인 소셜 서비스")
public class LoginRequestDto {

    @Schema(description = "타입", example = "KAKAO")
    private MemberType memberType;

}
