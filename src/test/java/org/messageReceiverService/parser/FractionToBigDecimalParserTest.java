package org.messageReceiverService.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FractionToBigDecimalParserTest {

    private FractionToBigDecimalParser parser = new FractionToBigDecimalParser();

    @Test
    @DisplayName("parse fraction")
    void parseFraction() {
        List.of("50/5","50/7","20/17","1/200","100/5")
                .forEach(str -> assertThat(parser.parse(str))
                        .isNotNull()
                        .isInstanceOf(BigDecimal.class)
                        .isPositive()
                );
    }

    @Test
    @DisplayName("zero divide by number")
    void fractionZeroDivideByNumber() {
        List.of("0/10", "0/200", "0/1354684684381846746843684984")
                .forEach(str -> assertThat(parser.parse(str))
                        .isNotNull()
                        .isInstanceOf(BigDecimal.class)
                        .isZero()
                );
    }

}