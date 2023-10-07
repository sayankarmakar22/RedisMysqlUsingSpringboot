package com.sayan.RedisMysqlUsingSpringboot.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@RedisHash("product")
public class RedisProduct implements Serializable {
    @Id
    private int id;
    private String name;
    private int price;
}
