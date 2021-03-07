package org.oakparkoak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 3/6/2021 20:04 PM
 */
@SpringBootApplication
public class ProgrammaticTransApp implements CommandLineRunner {
    @Autowired
    private TransactionTemplate trans;

    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticTransApp.class, args);
    }

    @Override
    public void run(String... args) {
        count();
        trans.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbc.execute("insert into FOO (NAME) values ('name-1')");
                count();
                transactionStatus.setRollbackOnly();
            }
        });
        count();

        //noinspection Convert2Lambda
        String name = trans.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                jdbc.execute("insert into FOO (NAME) values ('name-1')");
                return jdbc.queryForObject("select NAME from FOO", String.class);
            }
        });
        count();
        System.out.println(name);
    }

    private void count() {
        System.out.println(jdbc.queryForList("select count(*) as CNT from FOO").get(0).get("CNT"));
    }
}
