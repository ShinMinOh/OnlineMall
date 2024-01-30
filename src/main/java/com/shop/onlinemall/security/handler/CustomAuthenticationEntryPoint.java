package com.shop.onlinemall.security.handler;

import static com.shop.onlinemall.common.error.ErrorCode.AUTHENTICATION_ENTRY_POINT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.onlinemall.common.error.ErrorCode;
import com.shop.onlinemall.common.error.ProblemDetailFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    //유효한 자격증명 제공하지 않고 접근하려 할때 401

    setResponse(request, response);
  }

  //인증(서비스에 등록된 유저 인지 확인)
  // -> 토큰 생성
  // -> 유효한 토큰 인지 확인
  // -> 인가(해당 자원에 대한 권한이 있는지)
  private void setResponse(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ErrorCode errorCode = getErrorCode(request);
    response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    if(Objects.isNull(errorCode)){
      //에러코드가 담겨오지 않을경우 인증에 막혀서 온 케이스
      objectMapper.writeValue(response.getOutputStream(),
          ProblemDetailFactory.of(AUTHENTICATION_ENTRY_POINT, request.getRequestURI()));
    }else{
      // 에러코드가 담겨왔을 경우 그 에러코드 반환, ex) 유효하지 않은 토큰
      objectMapper.writeValue(response.getOutputStream(),
          ProblemDetailFactory.of(errorCode, request.getRequestURI()));
    }
  }

  private ErrorCode getErrorCode(HttpServletRequest request) {
    // request에서 name이 "exception"인 속성값(에러코드)을 가져옴.
    return (ErrorCode) request.getAttribute("exception");
  }
}
