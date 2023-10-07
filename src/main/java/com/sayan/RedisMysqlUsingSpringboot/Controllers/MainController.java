package com.sayan.RedisMysqlUsingSpringboot.Controllers;

import com.sayan.RedisMysqlUsingSpringboot.Model.Product;
import com.sayan.RedisMysqlUsingSpringboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class MainController {
    @Autowired
    private ProductServices productServices;

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productServices.saveProduct(product);
    }
    @GetMapping("/view/{id}")
    public Object view(@PathVariable int id){
        return productServices.viewProduct(id);
    }
    @PutMapping("/update")
    public Product update(@RequestBody Product product){
        return productServices.updateProduct(product);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable  int id){
        return productServices.deleteProduct(id);
    }
}
