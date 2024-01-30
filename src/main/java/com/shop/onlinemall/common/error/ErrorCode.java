package com.shop.onlinemall.common.error;

public enum ErrorCode {

  //Auth Error
  INVALID_JWT(401, "A1", "유효하지 않은 Jwt 토큰입니다"),
  ACCESS_DENIED(403, "A2", "권한이 없습니다."),
  AUTHENTICATION_ENTRY_POINT(401, "A3", "인증이 필요합니다"),
  // Common
  INVALID_INPUT_VALUE(400, "C1", "올바르지 않은 입력 값입니다"),
  INTERNAL_SERVER_ERROR(500, "C2", "서버 에러");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
