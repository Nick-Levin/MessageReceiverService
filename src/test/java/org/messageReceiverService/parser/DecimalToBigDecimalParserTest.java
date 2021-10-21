package org.messageReceiverService.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DecimalToBigDecimalParserTest {

    private DecimalToBigDecimalParser parser = new DecimalToBigDecimalParser();

    @Test
    @DisplayName("decimal string parsing")
    void decimalStringParsing() {
        List.of("135483381831.153484384684", "15.5", "12.87", "0.000000000000000005")
                .forEach(str ->
                        assertThat(parser.parse(str))
                                .isNotNull()
                                .isInstanceOf(BigDecimal.class)
                                .isPositive()
                );
    }
}