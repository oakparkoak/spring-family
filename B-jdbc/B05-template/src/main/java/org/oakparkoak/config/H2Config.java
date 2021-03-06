package org.oakparkoak.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: org.oakparkoak.config
 * @author: Captain
 * @time: 3/6/2021 8:09 PM
 */
@Configuration
public class H2Config {
    /**
     * http://localhost:8082
     * <p>
     * Database available at 'jdbc:h2:mem:testdb'
     *
     * @return
     * @throws SQLException
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
    }
}
