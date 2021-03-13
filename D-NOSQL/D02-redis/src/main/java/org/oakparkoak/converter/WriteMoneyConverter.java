package org.oakparkoak.converter;

import java.nio.charset.StandardCharsets;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * @package: org.oakparkoak.converter
 * @author: Captain
 * @time: 3/13/2021 9:02 PM
 */
@WritingConverter
public class WriteMoneyConverter implements Converter<Money, byte[]> {
    /**
     * Money to bytes
     *
     * @param money
     * @return bytes
     */
    @Override
    public byte[] convert(Money money) {
        String value = Long.toString(money.getAmountMinorLong());
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
