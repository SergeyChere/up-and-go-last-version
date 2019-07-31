package com.coffeeshop.controller.testController.productTest;

import com.coffeeshop.model.customer.entity.product.product.Product;
import com.coffeeshop.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductExampleForTestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products/example")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/products/example/{id}")
    public Product getById(@PathVariable("id")Long id){
        return productRepository
                .findById(id).orElseThrow(null);
    }

    @PostMapping("/products/example")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
