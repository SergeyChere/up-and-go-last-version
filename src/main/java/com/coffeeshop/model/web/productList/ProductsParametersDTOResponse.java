package com.coffeeshop.model.web.productList;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductsParametersDTOResponse {

    private String flavour;
    private Integer rate;
    private Boolean decaf;
    private String coffeeType;
}
