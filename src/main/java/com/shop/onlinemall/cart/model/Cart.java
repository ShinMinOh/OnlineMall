package com.shop.onlinemall.cart.model;

import com.shop.onlinemall.common.model.BaseMember;
import com.shop.onlinemall.common.model.BaseTime;
import com.shop.onlinemall.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  public Cart(Long id, User user) {
    this.id = id;
    this.user = user;
  }
}
