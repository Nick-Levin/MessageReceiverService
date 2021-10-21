package org.messageReceiverService.parser;

import java.math.BigDecimal;
import java.math.BigInteger;

public class HexadecimalToBigDecimalParser implements BigDecimalParser {

    public static final String REGEX_PATTERN = "^[0-9A-Fa-f]*?[A-Fa-f]+[0-9A-Fa-f]*?$";

    @Override
    public BigDecimal parse(String hex) {
        return new BigDecimal(new BigInteger(hex, 16));
    }
}
