package org.messageReceiverService.parser;

import java.math.BigDecimal;

public class DecimalToBigDecimalParser implements BigDecimalParser{

    public static final String REGEX_PATTERN = "[0-9]*\\.[0-9]*";

    @Override
    public BigDecimal parse(String string) {
        return new BigDecimal(string);
    }

}
