package com.shop.onlinemall.security.handler;

import static com.shop.onlinemall.common.error.ErrorCode.ACCESS_DENIED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.onlinemall.common.error.ProblemDetailFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    //필요한 권한 없이 접근하려 할때 403

    setResponse(request, response);
  }

  private void setResponse(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    objectMapper.writeValue(response.getOutputStream(),
        ProblemDetailFactory.of(ACCESS_DENIED, request.getRequestURI()));
  }
}
