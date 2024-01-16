package com.shop.onlinemall.cartitem.model;

import com.shop.onlinemall.cart.model.Cart;
import com.shop.onlinemall.common.model.BaseTime;
import com.shop.onlinemall.item.model.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_item_id")
  private Long id;

  private int count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @Builder
  public CartItem(Long id, int count, Cart cart, Item item) {
    this.id = id;
    this.count = count;
    this.cart = cart;
    this.item = item;
  }
}
