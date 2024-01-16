package com.shop.onlinemall.order;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.shop.onlinemall.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class OrderTest {

  @Test
  void order_엔티티_생성(){
    User user = User.builder().id(1L).build();

    Order order = Order.builder()
        .user(user)
        .orderStatus(OrderStatus.ORDER)
        .build();

    assertThat(order).isNotNull();
  }
}