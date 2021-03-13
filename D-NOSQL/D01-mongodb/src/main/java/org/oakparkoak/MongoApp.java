package org.oakparkoak;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.oakparkoak.model.Coffee;
import org.oakparkoak.repo.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 1/1/2021 0:00 PM
 */
@SpringBootApplication
public class MongoApp implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepo;

    @Autowired
    private MongoTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(MongoApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        testTemplate();
        System.out.println("\n====================\n");
        testRepo();
    }

    private void testRepo() throws InterruptedException {
        coffeeRepo.deleteAll();

        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).createTime(new Date()).updateTime(new Date()).build();
        Coffee latte = Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"), 30.0)).createTime(new Date()).updateTime(new Date()).build();

        coffeeRepo.insert(Arrays.asList(espresso, latte));
        coffeeRepo.findAll(Sort.by("name").descending()).forEach(System.out::println);

        Thread.sleep(3000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeRepo.save(latte);
        coffeeRepo.findByName("latte").forEach(System.out::println);
    }

    private void testTemplate() throws InterruptedException {
        DeleteResult deleteResult = template.remove(Query.query(Criteria.where("name").is("espresso")), Coffee.class);
        System.out.println("\ndeleteResult: " + deleteResult);

        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.ofMajor(CurrencyUnit.of("CNY"), 20))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee save = template.save(espresso);
        System.out.println("\nsave: " + save);

        Thread.sleep(3000);
        UpdateResult updateResult = template.updateFirst(
                Query.query(Criteria.where("name").is("espresso")),
                Update.update("price", Money.ofMajor(CurrencyUnit.of("CNY"), 25)).currentDate("updateTime"),
                Coffee.class);
        System.out.println("\nupdateResult: " + updateResult.getModifiedCount());

        System.out.println("\nfind: " + template.findById(save.getId(), Coffee.class));
    }
}
