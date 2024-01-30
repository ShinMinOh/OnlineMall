package com.shop.onlinemall.common.error.exception;

import com.shop.onlinemall.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorCode errorCode;

  //에러코드와 메시지 반환
  public BusinessException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  //에러코드 반환
  public BusinessException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }
}
