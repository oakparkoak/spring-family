package org.oakparkoak;

import org.oakparkoak.custom.OverrideException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 3/7/2021 20:04 PM
 * @see org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
 * @see /org/springframework/jdbc/support/sql-error-codes.xml
 */
@SpringBootApplication
public class SqlExceptionTranslatorApp implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(SqlExceptionTranslatorApp.class, args);
    }

    @Override
    public void run(String... args) {
        jdbc.execute("insert into FOO (ID, NAME) values (1, 'name-1')");

        try {
            jdbc.execute("insert into FOO (ID, NAME) values (1, 'name-1')");
        } catch (DataAccessException e) {
            System.out.println(e.getClass() == OverrideException.class);
            System.out.println(e.getMessage());
        }
    }
}
