package com.shop.onlinemall.item.model;

import com.shop.onlinemall.common.model.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  @Column(name = "item_name", nullable = false)
  private String itemName;                              //상품명

  @Column(name = "item_detail")
  private String itemDetail;                            //상품상세정보

  @Enumerated(EnumType.STRING)
  @Column(name = "item_status")
  private ItemStatus itemStatus;                        //판매상태

  @Column(name = "price", nullable = false)
  private int price;                                    //상품 가격

  @Column(name = "stock_number", nullable = false)
  private int stockNumber;                               //재고 수량



  @Builder
  public Item(Long id, String itemName, String itemDetail, ItemStatus itemStatus, int price,
      int stockNumber) {
    this.id = id;
    this.itemName = itemName;
    this.itemDetail = itemDetail;
    this.itemStatus = itemStatus;
    this.price = price;
    this.stockNumber = stockNumber;
  }
}
