package com.sayan.RedisMysqlUsingSpringboot.Services;

import com.sayan.RedisMysqlUsingSpringboot.Model.Product;
import com.sayan.RedisMysqlUsingSpringboot.Model.RedisProduct;
import com.sayan.RedisMysqlUsingSpringboot.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String HashKey = "product";
    @Autowired
    private RedisProduct redisProduct;
    public Product saveProduct(Product product){
        redisProduct.setId(product.getId());
        redisProduct.setPrice(product.getPrice());
        redisProduct.setName(product.getName());
        redisTemplate.opsForHash().put(HashKey,redisProduct.getId(),redisProduct);
        return productRepo.save(product);
    }
    public Object viewProduct(int id){
        Object foundProductFromRedis = redisTemplate.opsForHash().get(HashKey, id);
        if(foundProductFromRedis != null){
            return foundProductFromRedis;
        }
        return productRepo.findById(id);
    }
    public Product updateProduct(Product product){
        if(!productRepo.existsById(product.getId())){
            throw new RuntimeException("product not found");
        }
        Object foundProductFromRedis = redisTemplate.opsForHash().get(HashKey, product.getId());
        if(foundProductFromRedis != null){
            redisTemplate.opsForHash().delete(HashKey,product.getId());
            redisProduct.setId(product.getId());
            redisProduct.setPrice(product.getPrice());
            redisProduct.setName(product.getName());
            redisTemplate.opsForHash().put(HashKey,product.getId(),redisProduct);
        }

        Product foundProduct = productRepo.findById(product.getId()).orElseThrow(()-> new RuntimeException("not valid"));
        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        return productRepo.save(foundProduct);
    }
    public String deleteProduct(int id){
        redisTemplate.opsForHash().delete(HashKey,id);
        productRepo.deleteById(id);
        return "deleted " + id;
    }
}
