package com.sayan.RedisMysqlUsingSpringboot.Repository;

import com.sayan.RedisMysqlUsingSpringboot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
