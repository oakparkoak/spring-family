package org.oakparkoak;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/3/2021 12:45 PM
 */
@Slf4j
@SpringBootApplication
public class DatasourceConfigApp implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(DatasourceConfigApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        printConnection();

        jdbc.queryForList("SELECT * FROM FOO").forEach(foo -> log.info(foo.toString()));
    }

    private void printConnection() throws SQLException {
        log.info("DataSource: {}", dataSource.toString());

        Connection conn = dataSource.getConnection();
        log.info("Connection: {}", conn.toString());

        conn.close();
    }
}
