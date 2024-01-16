package com.shop.onlinemall.orderitem;


import static org.assertj.core.api.Assertions.*;

import com.shop.onlinemall.item.model.Item;
import com.shop.onlinemall.order.Order;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class OrderItemTest {

  @Test
  void orderItem_엔티티_생성(){
    Item item = Item.builder().id(1L).build();
    Order order = Order.builder().id(1L).build();

    OrderItem orderItem = OrderItem.builder()
        .id(1L)
        .item(item)
        .order(order)
        .count(10)
        .orderPrice(10000).build();

    assertThat(orderItem).isNotNull();
  }
}