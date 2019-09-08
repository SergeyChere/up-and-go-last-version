package com.coffeeshop.controller;

import com.coffeeshop.model.customer.web.checkout.CheckoutSubmitDTORequest;
import com.coffeeshop.model.customer.web.checkout.CheckoutSubmitDTOResponse;
import com.coffeeshop.model.customer.web.productList.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class MocksController {

    @PostMapping("/products")
    public List<ProductListDTOResponse> getProductList(@RequestBody ProductListDTORequest productListDTORequest) {
        return Stream.of(
                ProductListDTOResponse.builder()
                        .popularProductDTO(PopularProductDTOResponse.builder()
                                .productDTO(ProductDTOResponse.builder()
                                        .productId(147852L)
                                        .title("Third")
                                        .shortDescription("Some third description")
                                        .type("Coffee")
                                        .previewImage("Base64")
                                        .price(16.45)
                                        .availableAmount(32)
                                        .productsParametersDTO(ProductsParametersDTOResponse.builder()
                                                .strong(1)
                                                .bitter(2)
                                                .sour(3)
                                                .decaf(true)
                                                .coffeeType("arabika").build()).build()).build())
                        .productDTO(ProductDTOResponse.builder()
                                .productId(12345L)
                                .title("First")
                                .shortDescription("Some first description")
                                .type("Coffee")
                                .previewImage("Base64")
                                .price(78.25)
                                .availableAmount(78)
                                .productsParametersDTO(ProductsParametersDTOResponse.builder()
                                        .strong(3)
                                        .bitter(2)
                                        .sour(1)
                                        .decaf(false)
                                        .coffeeType("arabika").build()).build()).build()).collect(Collectors.toList());
    }

    @GetMapping(value = "/customer/products/{productId}/images/{imageId}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("productId") Long productId,
                                                      @PathVariable("imageId") String imageId)  throws IOException {
        final InputStream in = getClass()
                .getResourceAsStream("/images/black-coffee-bean-seed-shape-600w-1273417555.jpg");
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/customer/checkout")
    public CheckoutSubmitDTOResponse submitCheckout(@RequestBody @Valid CheckoutSubmitDTORequest request) {
        return CheckoutSubmitDTOResponse.builder().orderId(1L).build();
    }
}
