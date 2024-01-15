package com.shop.onlinemall.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public class BaseMember{

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;   //작성한 사람

  @LastModifiedBy
  private String modifiedBy;  //수정한 사람
}
