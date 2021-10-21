package org.messageReceiverService.parser;

import java.math.BigDecimal;
import java.math.MathContext;

public class FractionToBigDecimalParser implements BigDecimalParser{

    public static final String REGEX_PATTERN = "[0-9]+/[1-9]+[0-9]*";

    @Override
    public BigDecimal parse(String string) {
        String[] numbers = string.split("/");

        BigDecimal numerator = new BigDecimal(numbers[0]);
        BigDecimal denominator = new BigDecimal(numbers[1]);

        return numerator.divide(denominator, MathContext.DECIMAL64);
    }

}
