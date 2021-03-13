package org.oakparkoak.service;

import java.util.List;

import org.oakparkoak.model.Coffee;
import org.oakparkoak.repo.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @package: org.oakparkoak.service
 * @author: Captain
 * @time: 3/13/2021 9:49 PM
 */
@Service
@CacheConfig(cacheNames = "starbucks")
public class CoffeeService {
    @Autowired
    private CoffeeRepository repo;

    @CacheEvict
    public void evictCache() {
        System.out.println("\n==== Clear Cache ====\n");
    }

    @Cacheable
    public List<Coffee> findAllCoffee() {
        System.out.println("From DB ===> ");
        return repo.findAll();
    }
}
