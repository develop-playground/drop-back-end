package com.dailymap.dailymap.domain.member.model;

import com.dailymap.dailymap.domain.common.BaseEntity;
import com.dailymap.dailymap.domain.member.constant.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public void updateUsername(String username) {
        this.username = username;
    }

}
