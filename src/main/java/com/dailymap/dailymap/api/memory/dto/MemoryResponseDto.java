package com.dailymap.dailymap.api.memory.dto;

import com.dailymap.dailymap.domain.memory.model.Location;
import com.dailymap.dailymap.domain.memory.model.Memory;
import com.dailymap.dailymap.global.util.DateTimeUtils;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemoryResponseDto {

    private Long id;

    private List<String> imageUrls;

    private String content;

    private Location location;

    private String createdDate;

    public static MemoryResponseDto from(Memory memory) {
        return MemoryResponseDto.builder()
            .id(memory.getId())
            .imageUrls(memory.getImageUrls())
            .content(memory.getContent())
            .location(memory.getLocation())
            .createdDate(DateTimeUtils.convertToStringDateFormat(memory.getCreateTime()))
            .build();
    }

}
