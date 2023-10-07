package com.sayan.RedisMysqlUsingSpringboot.Services;

import com.sayan.RedisMysqlUsingSpringboot.Model.Product;
import com.sayan.RedisMysqlUsingSpringboot.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    private ProductRepo productRepo;

    public Product saveProduct(Product product){
        return productRepo.save(product);
    }
    public Product viewProduct(int id){
        return productRepo.findById(id).orElse(null);
    }
    public Product updateProduct(Product product){
        if(productRepo.existsById(product.getId())){
            Product foundProduct = productRepo.findById(product.getId()).orElseThrow(()-> new RuntimeException("product not found"));
            foundProduct.setName(product.getName());
            foundProduct.setPrice(product.getPrice());
            return productRepo.save(foundProduct);
        }
        throw new RuntimeException("something went with the entered product id");
    }
    public String deleteProduct(int id){
        productRepo.deleteById(id);
        return "deleted " + id;
    }
}
