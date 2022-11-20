package com.dailymap.dailymap.api.memory.dto;

import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.memory.model.Location;
import com.dailymap.dailymap.domain.memory.model.Memory;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface MemoryRequestDto {

    @Getter
    @Builder
    class Register {
        private List<String> imageUrls;

        private String content;

        private Location location;

        public Memory toEntity(Member member) {
            return Memory.builder()
                .content(content)
                .imageUrls(imageUrls)
                .location(location)
                .member(member)
                .build();
        }
    }

    @Builder
    @Getter
    class Update {
        private String content;
    }

}
