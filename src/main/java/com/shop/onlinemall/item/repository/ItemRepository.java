package com.shop.onlinemall.item.repository;

import com.shop.onlinemall.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
