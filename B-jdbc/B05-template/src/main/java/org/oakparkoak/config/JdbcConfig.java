package org.oakparkoak.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @package: org.oakparkoak.config
 * @author: Captain
 * @time: 3/6/2021 8:04 PM
 */
@Configuration
public class JdbcConfig {
    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbc) {
        return new SimpleJdbcInsert(jdbc).withTableName("FOO").usingGeneratedKeyColumns("ID");
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
