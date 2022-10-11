package com.dailymap.dailymap.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(EntityListeners.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
