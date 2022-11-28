package com.dailymap.dailymap.domain.memory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    private Double latitude;

    private Double longitude;

}
