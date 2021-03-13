package org.oakparkoak;

import org.oakparkoak.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 1/1/2021 0:00 PM
 * @see org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
 * @see org.springframework.boot.autoconfigure.cache.CacheProperties
 */
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class CacheApp implements ApplicationRunner {
    @Autowired
    private CoffeeService service;

    public static void main(String[] args) {
        SpringApplication.run(CacheApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        service.findAllCoffee();

        service.findAllCoffee();
        System.out.println("From Cache ===> ");
        service.findAllCoffee();
        System.out.println("From Cache ===> ");

        service.evictCache();
        service.findAllCoffee().forEach(System.out::println);
    }
}
