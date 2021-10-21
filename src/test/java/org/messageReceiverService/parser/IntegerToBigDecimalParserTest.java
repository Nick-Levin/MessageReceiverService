package org.messageReceiverService.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegerToBigDecimalParserTest {

    private IntegerToBigDecimalParser parser = new IntegerToBigDecimalParser();

    @Test
    @DisplayName("integer parsing")
    void integerParsing() {
        List.of("15","20","9000000","75634894941534386436841698496","44444442303535","1")
                .forEach(str -> assertThat(parser.parse(str))
                        .isNotNull()
                        .isInstanceOf(BigDecimal.class)
                        .isPositive()
                );
    }

}