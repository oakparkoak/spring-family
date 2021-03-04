package org.oakparkoak;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/8/2021 2:46 PM
 */
@Slf4j
@RestController
@SpringBootApplication
public class DatasourceDruidApp implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DatasourceDruidApp.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("DataSource: {}", dataSource.toString());
    }

    @GetMapping("/index")
    public void test() {
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(foo -> log.info(foo.toString()));
    }
}
