package com.dailymap.dailymap.domain.member.model;

import com.dailymap.dailymap.domain.common.BaseEntity;
import com.dailymap.dailymap.domain.member.constant.MemberType;
import com.dailymap.dailymap.domain.memory.model.Memory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    private String username;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String refreshToken;

    @Column(length = 250)
    private LocalDateTime tokenExpirationTime;

    @OneToMany(mappedBy = "member")
    private List<Memory> memories;

    public static Member of(String email, String name, MemberType memberType) {
        return Member.builder()
            .email(email)
            .username(name)
            .memberType(memberType)
            .build();
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateRefreshInfo(String refreshToken, LocalDateTime refreshTokenExpireTime) {
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = refreshTokenExpireTime;
    }

    public void expireRefreshToken() {
        this.tokenExpirationTime = LocalDateTime.now();
    }

    public boolean isSameEmail(String authEmail) {
        return email.equals(authEmail);
    }
}
