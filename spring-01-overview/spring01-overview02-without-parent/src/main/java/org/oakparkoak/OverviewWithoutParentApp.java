package org.oakparkoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 2/2/2021 12:56 PM
 */
@RestController
@SpringBootApplication
public class OverviewWithoutParentApp {
    public static void main(String[] args) {
        SpringApplication.run(OverviewWithoutParentApp.class, args);
    }

    @RequestMapping("/index")
    public String index() {
        return "Hello!";
    }
}