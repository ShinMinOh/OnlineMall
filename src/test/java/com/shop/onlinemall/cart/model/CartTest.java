package com.shop.onlinemall.cart.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.shop.onlinemall.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CartTest {

  @Test
  void Cart_엔티티_생성(){
    User user = User.builder().id(1L).build();

    Cart cart = Cart.builder()
        .id(1L)
        .user(user)
        .build();

    assertThat(cart).isNotNull();
  }
}