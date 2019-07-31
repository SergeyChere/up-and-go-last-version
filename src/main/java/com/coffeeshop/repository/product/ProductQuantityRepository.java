package com.coffeeshop.repository.product;

import com.coffeeshop.model.customer.entity.product.productQuantity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Long> {
}
