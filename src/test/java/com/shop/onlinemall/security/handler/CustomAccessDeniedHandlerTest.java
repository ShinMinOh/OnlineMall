package com.shop.onlinemall.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.onlinemall.common.error.ErrorCode;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.Charset;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CustomAccessDeniedHandlerTest {


  CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler(new ObjectMapper());

  MockHttpServletRequest request = new MockHttpServletRequest();

  MockHttpServletResponse response = new MockHttpServletResponse();

  @Test
  void 필요한_권한_없이_접근하려_할떼_403_응답을_한다() throws IOException {

    customAccessDeniedHandler.handle(request, response, new AccessDeniedException("Denied"));

    String content = response.getContentAsString(Charset.defaultCharset());

    System.out.println("content "+content);
    Assertions.assertThat(content).contains(ErrorCode.ACCESS_DENIED.getCode());
  }
}