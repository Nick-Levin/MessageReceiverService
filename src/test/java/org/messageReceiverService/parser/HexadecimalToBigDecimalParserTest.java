package org.messageReceiverService.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HexadecimalToBigDecimalParserTest {

    private HexadecimalToBigDecimalParser parser = new HexadecimalToBigDecimalParser();

    @Test
    @DisplayName("hexadecimal parsing")
    void hexadecimalParsing() {
        List.of("AFFFffFBbDccCC","15ff15cc","Cc13DD48","FFDDAABBf8e8d4c4b2a2")
                .forEach(str -> assertThat(parser.parse(str))
                                .isNotNull()
                                .isInstanceOf(BigDecimal.class)
                                .isPositive()
                        );
    }

}