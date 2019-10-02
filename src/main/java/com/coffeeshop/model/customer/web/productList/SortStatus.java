package com.coffeeshop.model.customer.web.productList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortStatus {

    PRICE(" p.unitPrice, p.productName"),
    NAME(" p.productName, p.unitPrice");

    private final String name;

    public String toString() {
        return this.name;
    }
}
