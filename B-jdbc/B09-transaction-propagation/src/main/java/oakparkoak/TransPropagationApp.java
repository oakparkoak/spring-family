package oakparkoak;

import oakparkoak.service.IFooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 3/7/2021 19:43 PM
 */
@SpringBootApplication
public class TransPropagationApp implements CommandLineRunner {
    @Autowired
    private IFooService service;

    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(TransPropagationApp.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            service.insertOuter();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(jdbc.queryForList("select count(*) from FOO", Long.class));
            jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);
        }
    }
}
