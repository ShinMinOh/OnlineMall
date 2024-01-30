package com.shop.onlinemall.security;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.shop.onlinemall.security.exception.InvalidJwtException;
import com.shop.onlinemall.user.Role;
import com.shop.onlinemall.user.User;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class JwtTokenProviderTest {

  //32자 이상
  JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("jwt-secret-key-for-test-profile-setting");

  @Test
  void 유저정보에_해당하는_access_token을_생성하고_반환한다(){
    String accessToken = getStringAccessToken();

    assertThat(accessToken).isNotNull();
  }

  @Test
  void 유저정보에_해당하는_refresh_token을_생성하고_반환한다(){
    User user = User.builder().id(1L).role(Role.ROLE_USER).build();

    String refreshToken = jwtTokenProvider.createRefreshToken(user);

    assertThat(refreshToken).isNotNull();
  }

  @Test
  void 토큰이_null_일경우_InvalidJwtException_예외가_발생한다(){
  assertThatThrownBy(() -> jwtTokenProvider.validateToken(null))
      .isInstanceOf(InvalidJwtException.class);
  }

  @Test
  void 토큰이_빈_문자열_일경우_InvalidJwtException_예외가_발생한다(){
  assertThatThrownBy(() -> jwtTokenProvider.validateToken(""))
      .isInstanceOf(InvalidJwtException.class);
  }

  @Test
  void 유효한_토큰을_검증한다(){
    String accessToken = getStringAccessToken();

    jwtTokenProvider.validateToken(accessToken);
  }

  @Test
  void 토큰에서_subject를_반환한다(){
    String accessToken = getStringAccessToken();

    String subject = jwtTokenProvider.getSubjectFromToken(accessToken);

    assertThat(subject).isNotNull();
  }



  @Test
  void 토큰에서_role을_반환한다(){
    String accessToken = getStringAccessToken();

    String role = jwtTokenProvider.getRoleFromToken(accessToken);

    assertThat(role).isNotNull();

  }

  private String getStringAccessToken() {
    User user = User.builder().id(1L).role(Role.ROLE_USER).build();
    return jwtTokenProvider.createAccessToken(user);
  }
}