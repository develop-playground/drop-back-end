package com.dailymap.dailymap.domain.memory.model;

import com.dailymap.dailymap.domain.common.BaseEntity;
import com.dailymap.dailymap.domain.member.model.Member;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> imageUrls;

    @Column(length = 1000)
    private String content;

    @Embedded
    private Location location = new Location();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
