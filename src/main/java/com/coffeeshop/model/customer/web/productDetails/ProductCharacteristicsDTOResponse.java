package com.coffeeshop.model.customer.web.productDetails;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductCharacteristicsDTOResponse {

    private Integer strong;
    private Integer sour;
    private Integer bitter;
}
