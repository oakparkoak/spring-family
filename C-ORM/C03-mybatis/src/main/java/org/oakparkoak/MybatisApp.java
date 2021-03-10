package org.oakparkoak;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.oakparkoak.mapper.CoffeeMapper;
import org.oakparkoak.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.MybatisAutoConfiguration -> scanner.setAnnotationClass(Mapper.class)
 * <p>
 * 2.@MapperScan("org.oakparkoak.mapper")
 *
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 1/1/2021 0:00 PM
 */
@SpringBootApplication
@MapperScan("org.oakparkoak.mapper")
public class MybatisApp implements ApplicationRunner {
    @Autowired
    private CoffeeMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Coffee coffee = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        mapper.save(coffee);
        System.out.println("\nID: " + coffee.getId());

        coffee = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
        mapper.save(coffee);
        System.out.println("\nID: " + coffee.getId());

        coffee = mapper.findById(coffee.getId());
        System.out.println("\nCoffee: " + coffee);
    }
}
