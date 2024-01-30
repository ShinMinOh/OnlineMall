package com.shop.onlinemall.security.filter;

import static com.shop.onlinemall.common.error.ErrorCode.INVALID_JWT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shop.onlinemall.security.JwtTokenProvider;
import com.shop.onlinemall.security.exception.InvalidJwtException;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

  @InjectMocks
  JwtFilter jwtFilter;

  @Mock
  JwtTokenProvider jwtTokenProvider;

  MockHttpServletResponse response = new MockHttpServletResponse();
  MockHttpServletRequest request = new MockHttpServletRequest();
  MockFilterChain filterChain = new MockFilterChain();

  final String AUTHORIZATION_HEADER = "Authorization";
  final String AUTHORIZATION_TYPE = "Bearer ";
  final String TOKEN = "TestToken";

  @AfterEach
  void resetHeader(){
    // Default : MODE_THREADLOCAL
    // 인증이 완료되면 HttpSession에 저장되어 어플리케이션 전반에 걸쳐 참조가 가능해, clear 필요
    SecurityContextHolder.clearContext();
  }

  @Test
  void 인증_헤더에_유효한_토큰이_있으면_인증_객체가_저장된다() throws ServletException, IOException {
  request.addHeader(AUTHORIZATION_HEADER, AUTHORIZATION_TYPE + TOKEN);

  //인증 헤더에 token이 validate하다 가정하고 건너뛰고 다음 로직 수행
  doNothing().when(jwtTokenProvider).validateToken(TOKEN);
  when(jwtTokenProvider.getSubjectFromToken(TOKEN)).thenReturn("1L");
  when(jwtTokenProvider.getRoleFromToken(TOKEN)).thenReturn("ROLE_USER");

  jwtFilter.doFilterInternal(request, response, filterChain);

  assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
  }

  @Test
  void 인증_헤더에_유효한_토큰이_없으면_인증_객체가_저장되지_않는다() throws ServletException, IOException {
    request.addHeader(AUTHORIZATION_HEADER, AUTHORIZATION_TYPE + TOKEN);

    //TOKEN값이 유효하지 않아 INVALID_JWT를 터트림.
    doThrow(new InvalidJwtException("Error", INVALID_JWT)).when(jwtTokenProvider).validateToken(TOKEN);

    jwtFilter.doFilterInternal(request, response, filterChain);

    assertAll(
        () -> assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull(),
        () -> verify(jwtTokenProvider, never()).getSubjectFromToken(anyString()),
        () -> verify(jwtTokenProvider, never()).getRoleFromToken(anyString()),
        () -> assertThat(request.getAttribute("exception")).isEqualTo(INVALID_JWT)
    );

  }

  @Test
  void 인증_타입이_다르면_인증_객체가_저장되지_않는다() throws ServletException, IOException {
  request.addHeader(AUTHORIZATION_HEADER, TOKEN);

  doThrow(new InvalidJwtException("Error", INVALID_JWT)).when(jwtTokenProvider).validateToken(null);

  jwtFilter.doFilterInternal(request, response, filterChain);

  assertAll(
      () -> assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull(),
      () -> verify(jwtTokenProvider, never()).getSubjectFromToken(anyString()),
      () -> verify(jwtTokenProvider, never()).getRoleFromToken(anyString()),
      () -> assertThat(request.getAttribute("exception")).isEqualTo(INVALID_JWT)
  );

  }

  @Test
  void 인증_헤더가_없으면_인증_객체가_저장되지_않는다() throws ServletException, IOException {
    doThrow(new InvalidJwtException("Error", INVALID_JWT)).when(jwtTokenProvider).validateToken(null);

    jwtFilter.doFilterInternal(request, response, filterChain);

    assertAll(
        () -> assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull(),
        () -> verify(jwtTokenProvider, never()).getSubjectFromToken(anyString()),
        () -> verify(jwtTokenProvider, never()).getRoleFromToken(anyString()),
        () -> assertThat(request.getAttribute("exception")).isEqualTo(INVALID_JWT)
    );
  }
}