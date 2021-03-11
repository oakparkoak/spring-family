package org.oakparkoak;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.oakparkoak.mapper.auto.CoffeeMapper;
import org.oakparkoak.mapper.manual.CoffeePageMapper;
import org.oakparkoak.model.auto.Coffee;
import org.oakparkoak.model.auto.CoffeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: org.oakparkoak
 * @author: Captain
 * @time: 1/1/2021 0:00 PM
 */
@Slf4j
@SpringBootApplication
@MapperScan("org.oakparkoak.mapper")
public class MybatisComplexApp implements ApplicationRunner {
    @Autowired
    private CoffeeMapper autoMapper;

    @Autowired
    private CoffeePageMapper pageMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisComplexApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        autoTest();

        System.out.println("===== Auto Test End =====");

        manualTest();
    }

    private void autoTest() {
        Coffee espresso = new Coffee()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        autoMapper.insert(espresso);
        System.out.println("espresso: " + espresso.getId());

        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        autoMapper.insert(latte);
        System.out.println("latte: " + latte.getId());

        Coffee coffee = autoMapper.selectByPrimaryKey(1L);
        System.out.println("\ncoffee: " + coffee);

        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("latte");
        example.or().andNameEqualTo("espresso");
        List<Coffee> coffees = autoMapper.selectByExample(example);
        coffees.forEach(System.out::println);
    }

    private void manualTest() {
        System.out.println("\n1");
        pageMapper.listWithRowBounds(new RowBounds(1, 10)).forEach(System.out::println);

        System.out.println("\n2");
        pageMapper.listWithRowBounds(new RowBounds(2, 3)).forEach(System.out::println);

        System.out.println("\n3");
        pageMapper.listWithRowBounds(new RowBounds(3, 3)).forEach(System.out::println);

        System.out.println("\n4");
        pageMapper.listWithRowBounds(new RowBounds(1, 0)).forEach(System.out::println);

        System.out.println("\n5");
        pageMapper.list(1, 10).forEach(System.out::println);

        System.out.println("\n6");
        List<Coffee> list = pageMapper.list(2, 3);
        list.forEach(System.out::println);

        System.out.println("\n7" + new PageInfo<>(list));
    }

    /**
     * 1.Run mvn plugin
     * <p>
     * 2.Run generate
     *
     * @throws Exception
     */
    private void generate() throws Exception {
        System.out.println("Generate Start ...");
        List<String> warnings = new ArrayList<>();

        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration config = parser.parseConfiguration(getClass().getResourceAsStream("/generatorConfig.xml"));

        ShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        System.out.println("Generate Successful ...");
    }
}
