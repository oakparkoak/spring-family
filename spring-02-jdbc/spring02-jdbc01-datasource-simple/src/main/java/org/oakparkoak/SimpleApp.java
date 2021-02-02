package org.oakparkoak;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/2/2021 6:23 PM
 */
@Configuration
@EnableTransactionManagement
public class SimpleApp {
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        print(context);
    }

    private static void print(ApplicationContext context) throws SQLException {
        System.out.println("Beans: "+Arrays.toString(context.getBeanDefinitionNames()));

        SimpleApp demo = context.getBean("simpleApp", SimpleApp.class);
        demo.printDb();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    private void printDb() throws SQLException {
        System.out.println("DataSource: "+dataSource.toString());

        Connection conn = dataSource.getConnection();
        System.out.println("Connection: "+conn.toString());

        conn.close();
        System.out.println("Connection: "+conn.toString());
    }
}
