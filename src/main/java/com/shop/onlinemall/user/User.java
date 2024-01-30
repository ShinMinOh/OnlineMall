package com.shop.onlinemall.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String email;

  private String name;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public User(Long id, String email, String name, String password, Role role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
  }
}
