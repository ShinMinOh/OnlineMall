package com.shop.onlinemall.security.exception;

import com.shop.onlinemall.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidJwtException extends RuntimeException{

  private ErrorCode errorCode;

  public InvalidJwtException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
