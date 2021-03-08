package org.oakparkoak;

import java.util.Arrays;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.oakparkoak.model.Coffee;
import org.oakparkoak.model.Order;
import org.oakparkoak.model.OrderStatusEnum;
import org.oakparkoak.repo.CoffeeRepo;
import org.oakparkoak.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 3/8/2021 6:23 PM
 */
@Slf4j
@SpringBootApplication
public class JpaComplexApp implements ApplicationRunner {
    @Autowired
    private CoffeeRepo coffeeRepo;

    @Autowired
    private OrderRepo orderRepo;

    public static void main(String[] args) {
        SpringApplication.run(JpaComplexApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        save();

        list();

        updatable();
    }

    private void save() {
        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        Coffee latte = Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"), 30.0)).build();
        coffeeRepo.saveAll(Arrays.asList(espresso, latte));

        Order jack = Order.builder().customer("Jack").coffees(Collections.singletonList(latte)).status(OrderStatusEnum.PAID).build();
        Order barbossa = Order.builder().customer("Barbossa").coffees(Arrays.asList(espresso, latte)).status(OrderStatusEnum.INIT).build();
        orderRepo.saveAll(Arrays.asList(jack, barbossa));
    }

    private void list() {
        coffeeRepo.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(coffee -> log.error("COFFEE: {}", coffee));

        orderRepo.findTop3ByOrderByUpdateTimeDescIdAsc().forEach(order -> log.error("ORDER1: {}", order));

        orderRepo.findByCoffees_Name("espresso").forEach(order -> log.error("ORDER2: {}", order));

        orderRepo.findByCoffeesName("latte").forEach(order -> log.error("ORDER3: {}", order));

        orderRepo.findByCustomerOrderById("Barbossa").forEach(order -> log.error("ORDER-COFFEES: {}", order.getCoffees()));
    }

    /**
     * Add updatable = false to customer filed for testing.
     * <p>
     * Column(updatable = false)
     * private String customer
     */
    private void updatable() {
        Order order = orderRepo.findById(1L).get();
        order.setCustomer("Jack Sparrow");
        orderRepo.save(order);
    }
}
