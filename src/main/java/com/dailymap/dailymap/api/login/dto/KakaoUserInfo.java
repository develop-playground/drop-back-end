package com.dailymap.dailymap.api.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@JsonIgnoreProperties({"connected_at"})
public class KakaoUserInfo {

    private String id;

    private Map<String, String> properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter @Setter
    @JsonIgnoreProperties({"profile_nickname_needs_agreement", "profile_image_needs_agreement", "profile", "has_email",
        "email_needs_agreement", "is_email_valid", "is_email_verified"})
    public static class KakaoAccount {
        private String email;
    }

}
