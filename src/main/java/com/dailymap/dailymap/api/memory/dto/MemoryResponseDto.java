package com.dailymap.dailymap.api.memory.dto;

import com.dailymap.dailymap.domain.memory.model.Location;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.global.util.DateTimeUtils;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface MemoryResponseDto {

    @Getter
    @Builder
    class Register {
        private Long id;

        private List<String> imageUrls;

        private String content;

        private Location location;

        private String address;

        private String createdDate;

        public static MemoryResponseDto.Register from(Memory memory) {
            return Register.builder()
                .id(memory.getId())
                .imageUrls(memory.getImageUrls())
                .content(memory.getContent())
                .location(memory.getLocation())
                .address(memory.getAddress())
                .createdDate(DateTimeUtils.convertToStringDateFormat(memory.getCreateTime()))
                .build();
        }
    }

    @Builder
    @Getter
    class Update {
        private Long id;

        private String content;

        public static MemoryResponseDto.Update from(Memory memory) {
            return Update
                .builder()
                .id(memory.getId())
                .content(memory.getContent())
                .build();
        }
    }

    @Builder
    @Getter
    class Find {
        private Long id;

        private List<String> imageUrls;

        private String content;

        private Location location;

        private String address;

        private String createdDate;

        public static MemoryResponseDto.Find from(Memory memory) {
            return Find.builder()
                .id(memory.getId())
                .imageUrls(memory.getImageUrls())
                .content(memory.getContent())
                .location(memory.getLocation())
                .address(memory.getAddress())
                .createdDate(DateTimeUtils.convertToStringDateFormat(memory.getCreateTime()))
                .build();
        }
    }

}
