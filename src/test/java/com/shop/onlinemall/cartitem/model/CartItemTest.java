package com.shop.onlinemall.cartitem.model;

import static org.junit.jupiter.api.Assertions.*;

import com.shop.onlinemall.cart.model.Cart;
import com.shop.onlinemall.item.model.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CartItemTest {

  @Test
  void CartItem_엔티티_생성(){
    Cart cart = Cart.builder().id(1L).build();
    Item item = Item.builder().id(1L).build();

    CartItem cartItem = CartItem.builder()
        .id(1L)
        .count(10)
        .cart(cart)
        .item(item)
        .build();

    Assertions.assertThat(cartItem).isNotNull();
  }
}