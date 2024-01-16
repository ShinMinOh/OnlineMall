package com.shop.onlinemall.user;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class UserTest {

  @Test
  void User_엔티티_생성(){
    User user = User.builder().id(1L)
        .address("address")
        .email("email")
        .name("name")
        .password("1111")
        .role(Role.USER).build();

    Assertions.assertThat(user).isNotNull();
  }
}