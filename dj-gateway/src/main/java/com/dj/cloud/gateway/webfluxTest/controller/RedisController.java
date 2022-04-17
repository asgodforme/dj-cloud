package com.dj.cloud.gateway.webfluxTest.controller;

import com.dj.cloud.gateway.webfluxTest.config.RedisConfig;
import com.dj.cloud.gateway.webfluxTest.pojo.Customer;
import com.dj.cloud.gateway.webfluxTest.repository.CustomerRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/redis")
@ConditionalOnBean(RedisConfig.class)
public class RedisController {

    @Autowired
    private CustomerRedisRepository customerRedisRepository;

    @GetMapping("/saveCustomer")
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setId("Customer001");
        customer.setFirstName("姜");
        customer.setLastName("杰");
        customer.setAge(29);
        customer.setAddress("湖南省岳阳市岳阳楼区湖滨派出所对面");
        customerRedisRepository.saveCustomer(customer);
    }

    @GetMapping("/getCustomer")
    public Map<Object, Object> getCustomer() {
        return customerRedisRepository.getCustomer();
    }
}
