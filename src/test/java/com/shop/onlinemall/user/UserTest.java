package com.shop.onlinemall.user;

import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class UserTest {

  @Test
  void User_엔티티_생성(){
    User user = User.builder().id(1L)
        .email("email")
        .name("name")
        .password("1111")
        .role(Role.ROLE_USER).build();

    Assertions.assertThat(user).isNotNull();
  }
}