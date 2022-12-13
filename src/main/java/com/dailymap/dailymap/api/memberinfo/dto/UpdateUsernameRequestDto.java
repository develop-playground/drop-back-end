package com.dailymap.dailymap.api.memberinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "회원정보 수정")
public class UpdateUsernameRequestDto {

    @Schema(description = "변경하고자하는 닉네임", example = "드뢉")
    private String username;

}
