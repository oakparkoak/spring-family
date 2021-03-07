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
 * @time: 3/6/2021 20:43 PM
 */
@SpringBootApplication
public class DeclarativeTransApp implements CommandLineRunner {
    @Autowired
    private IFooService service;

    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransApp.class, args);
    }

    @Override
    public void run(String... args) {
        service.insert();
        System.out.println(jdbc.queryForList("select count(*) from FOO", Long.class));
        jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);

        try {
            service.insertWithRollback();
        } catch (RuntimeException e) {
            System.out.println(jdbc.queryForList("select count(*) from FOO where NAME = 'name-2'", Long.class));
            jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);
        }

        try {
            service.invokeSelfWithThis();
        } catch (RuntimeException e) {
            System.out.println(jdbc.queryForList("select count(*) from FOO", Long.class));
            jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);
        }

        try {
            service.invokeSelfWithProxy();
        } catch (RuntimeException e) {
            System.out.println(jdbc.queryForList("select count(*) from FOO", Long.class));
            jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);
        }
    }
}
