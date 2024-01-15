package com.shop.onlinemall.item.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.shop.onlinemall.common.annotation.CustomDataJpaTest;
import com.shop.onlinemall.item.model.Item;
import com.shop.onlinemall.item.model.ItemStatus;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@CustomDataJpaTest
class ItemRepositoryTest {

  @Autowired
  ItemRepository itemRepository;

  @Test
  void Item_엔티티_저장한다(){
    Item item = Item.builder()
        .id(1L)
        .itemName("name")
        .itemDetail("detail")
        .itemStatus(ItemStatus.SOLD_OUT)
        .price(1000)
        .stockNumber(10)
        .build();

    Item saveItem = itemRepository.save(item);

    assertAll(
        () -> assertThat(saveItem.getId()).isNotNull(),
        () -> assertThat(saveItem.getItemName()).isNotNull(),
        () -> assertThat(saveItem.getItemDetail()).isNotNull(),
        () -> assertThat(saveItem.getItemStatus()).isNotNull(),
        () -> assertThat(saveItem.getPrice()).isNotNull(),
        () -> assertThat(saveItem.getStockNumber()).isNotNull()
    );

  }

  @Test
  @Sql("findById.sql")
  void id에_해당하는_상품이_있다면_상품이_담긴_Optional_객체를_반환한다(){
    Optional<Item> findItem = itemRepository.findById(1L);

    assertThat(findItem).isPresent();
  }
}