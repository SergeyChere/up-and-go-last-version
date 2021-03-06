package com.coffeeshop.model.customer.entity.product.productCoffee;

import com.coffeeshop.model.customer.entity.base.BaseDate;
import com.coffeeshop.model.customer.entity.product.product.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "PRODUCT_COFFEE")
public class ProductCoffee extends BaseDate {

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @Column(name = "SOUR", nullable = false)
    private Integer sour;

    @Column(name = "BITTER", nullable = false)
    private Integer bitter;

    @Column(name = "STRONG", nullable = false)
    private Integer strong;

    @Column(name = "GROUND", nullable = false)
    private Boolean ground;

    @Column(name = "DECAF", nullable = false)
    private Boolean decaf;
}
