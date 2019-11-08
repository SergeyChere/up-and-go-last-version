package com.coffeeshop.job;

import com.coffeeshop.model.customer.entity.product.productItem.ProductItem;
import com.coffeeshop.model.customer.entity.product.productItem.status.ProductStatus;
import com.coffeeshop.model.customer.entity.product.productQuantity.ProductQuantity;
import com.coffeeshop.repository.product.ProductItemRepository;
import com.coffeeshop.repository.product.ProductQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductQuantityJob {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Scheduled(fixedRate = 120000)
    public void productQuantityJob(){

        List<ProductQuantity> quantityList = productQuantityRepository.findAll();
        for(ProductQuantity productQuantity : quantityList){
            List<ProductItem> items =productItemRepository.findAllByStatusAndProductId(ProductStatus.AVAILABLE,productQuantity.getProduct().getId());
            if(items.size() != productQuantity.getQuantity()){
                productQuantity.setQuantity(items.size());
            }
        }
        productQuantityRepository.saveAll(quantityList);
    }
}
