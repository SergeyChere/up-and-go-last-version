package com.coffeeshop.repository.product;

import com.coffeeshop.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}