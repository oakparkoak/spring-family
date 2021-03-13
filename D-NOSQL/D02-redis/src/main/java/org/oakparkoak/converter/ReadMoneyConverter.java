package org.oakparkoak.converter;

import java.nio.charset.StandardCharsets;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * @package: org.oakparkoak.converter
 * @author: Captain
 * @time: 3/13/2021 9:03 PM
 */
@ReadingConverter
public class ReadMoneyConverter implements Converter<byte[], Money> {
    /**
     * bytes to money
     *
     * @param bytes
     * @return money
     */
    @Override
    public Money convert(byte[] bytes) {
        String value = new String(bytes, StandardCharsets.UTF_8);
        return Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(value));
    }
}
