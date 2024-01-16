package com.shop.onlinemall.item.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class ItemTest {

  @Test
  void Item_엔티티_생성(){
    Item item = Item.builder()
        .id(1L)
        .itemName("name")
        .itemDetail("detail")
        .itemStatus(ItemStatus.SELL)
        .price(1000)
        .stockNumber(10).build();

    assertThat(item).isNotNull();
  }
}