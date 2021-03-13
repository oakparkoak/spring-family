package org.oakparkoak.converter;

import java.util.Collections;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.stereotype.Component;

/**
 * Just read to obj from bson
 * <p>
 * MongoDataAutoConfiguration
 * => MongoDbFactoryDependentConfiguration#MongoTemplate
 * => MongoConverter => MappingMongoConverter => MongoCustomConversions
 * => MongoDataConfiguration#MongoCustomConversions
 *
 * @package: org.oakparkoak.converter
 * @author: Captain
 * @time: 3/13/2021 3:59 PM
 */
@Component
public class MoneyConverter implements Converter<Document, Money> {
    @Override
    public Money convert(Document document) {
        Document money = (Document)document.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document)money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency), amount);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new MoneyConverter()));
    }
}
