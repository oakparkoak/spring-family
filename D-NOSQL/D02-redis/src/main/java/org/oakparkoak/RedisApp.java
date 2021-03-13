package org.oakparkoak;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.oakparkoak.model.Coffee;
import org.oakparkoak.model.CoffeeCache;
import org.oakparkoak.repo.CoffeeCacheRepository;
import org.oakparkoak.repo.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 1/1/2021 0:00 PM
 */
@SuppressWarnings("ConstantConditions")
@SpringBootApplication
public class RedisApp implements ApplicationRunner {
    private final static String COFFEE_CACHE_KEY = "starbucks";

    private final static String MOCHA = "mocha";

    private final static String LATTE = "latte";

    @Autowired
    private CoffeeRepository repo;

    @Autowired
    private CoffeeCacheRepository cacheRepo;

    @Autowired
    private RedisTemplate<String, Coffee> template;

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        testTemplate();
        testTemplate();
        testTemplate();
        testTemplate();
        System.out.println("\n=============\n");
        testRepo();
        testRepo();
        testRepo();
        testRepo();
    }

    private void testTemplate() {
        HashOperations<String, Object, Object> hash = template.opsForHash();
        if (template.hasKey(COFFEE_CACHE_KEY) && hash.hasKey(COFFEE_CACHE_KEY, MOCHA)) {
            System.out.println("Template From Redis: " + hash.get(COFFEE_CACHE_KEY, MOCHA));
        } else {
            ExampleMatcher example = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
            Optional<Coffee> coffee = repo.findOne(Example.of(Coffee.builder().name(MOCHA).build(), example));
            coffee.ifPresent(mocha -> {
                hash.put(COFFEE_CACHE_KEY, MOCHA, mocha);
                template.expire(COFFEE_CACHE_KEY, 5, TimeUnit.MINUTES);
                System.out.println("Template From DB: " + mocha);
            });
        }
    }

    private void testRepo() {
        Optional<CoffeeCache> coffeeCache = cacheRepo.findOneByName(LATTE);
        if (coffeeCache.isPresent()) {
            CoffeeCache cache = coffeeCache.get();
            System.out.println("Repo From Redis: " + Coffee.builder().name(cache.getName()).price(cache.getPrice()).build());
        } else {
            ExampleMatcher example = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
            Optional<Coffee> coffee = repo.findOne(Example.of(Coffee.builder().name(LATTE).build(), example));
            coffee.ifPresent(latte -> {
                cacheRepo.save(CoffeeCache.builder().id(latte.getId()).name(latte.getName()).price(latte.getPrice()).build());
                System.out.println("Repo From DB: " + latte);
            });
        }
    }
}
