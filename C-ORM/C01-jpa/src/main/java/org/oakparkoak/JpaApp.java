package org.oakparkoak;

import java.util.Arrays;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.oakparkoak.model.Coffee;
import org.oakparkoak.model.Order;
import org.oakparkoak.repo.CoffeeRepo;
import org.oakparkoak.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/2/2021 6:23 PM
 */
@SpringBootApplication
public class JpaApp implements ApplicationRunner {
    @Autowired
    private CoffeeRepo coffeeRepo;

    @Autowired
    private OrderRepo orderRepo;

    public static void main(String[] args) {
        SpringApplication.run(JpaApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepo.save(espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepo.save(latte);

        Order order = Order.builder()
                .customer("Elijah")
                .coffees(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        orderRepo.save(order);

        System.out.println("\nCoffees: " + coffeeRepo.findAll() + "\n");
        System.out.println("\nOrders: " + orderRepo.findAll());
    }
}
