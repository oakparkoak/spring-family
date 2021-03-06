package org.oakparkoak;

import lombok.extern.slf4j.Slf4j;
import org.oakparkoak.repository.BatchFooRepository;
import org.oakparkoak.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/8/2021 6:04 PM
 */
@Slf4j
@SpringBootApplication
public class JdbcTemplateApp implements CommandLineRunner {
    @Autowired
    FooRepository fooRepo;

    @Autowired
    BatchFooRepository batchFooRepo;

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApp.class, args);
    }

    @Override
    public void run(String... args) {
        fooRepo.insert();

        batchFooRepo.batchInsert();

        fooRepo.list();
    }
}
