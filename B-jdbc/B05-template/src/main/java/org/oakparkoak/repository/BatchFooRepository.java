package org.oakparkoak.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.oakparkoak.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

/**
 * @package: org.oakparkoak.repository
 * @author: Captain
 * @time: 3/6/2021 7:57 PM
 */
@Repository
public class BatchFooRepository {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate namedParamJdbc;

    public void batchInsert() {
        List<Foo> rows = new ArrayList<>();
        rows.add(Foo.builder().id(10L).name("name-5").build());
        rows.add(Foo.builder().name("name-6").build());
        namedParamJdbc.batchUpdate("insert into FOO (ID, NAME) values (:id, :name)", SqlParameterSourceUtils.createBatch(rows));

        jdbc.batchUpdate("insert into FOO  (NAME) values (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, "BatchSize-" + i);
            }

            @Override
            public int getBatchSize() {
                return 4;
            }
        });
    }
}
