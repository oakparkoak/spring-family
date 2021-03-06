package org.oakparkoak.repository;

import java.util.Arrays;
import java.util.HashMap;

import org.oakparkoak.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * @package: org.oakparkoak.repository
 * @author: Captain
 * @time: 3/6/2021 7:43 PM
 */
@Repository
public class FooRepository {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private SimpleJdbcInsert simpleJdbc;

    public void insert() {
        Arrays.asList("name-2", "name-3").forEach(name -> jdbc.update("insert into FOO (NAME) values (?)", name));

        HashMap<String, String> row = new HashMap<>(2);
        row.put("Name", "name-4");
        simpleJdbc.execute(row);
    }

    public void list() {
        System.out.println("Count: " + jdbc.queryForObject("select count(*) from FOO", Long.class));

        jdbc.queryForList("select NAME from FOO", String.class).forEach(System.out::println);

        jdbc.query("select * from FOO", (rs, rowNum) -> Foo.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .build())
                .forEach(System.out::println);
    }
}
