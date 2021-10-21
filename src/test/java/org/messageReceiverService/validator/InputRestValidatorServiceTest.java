package org.messageReceiverService.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.messageReceiverService.parser.DecimalToBigDecimalParser;
import org.messageReceiverService.parser.FractionToBigDecimalParser;
import org.messageReceiverService.parser.HexadecimalToBigDecimalParser;
import org.messageReceiverService.parser.IntegerToBigDecimalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InputRestValidatorServiceTest {

    @Autowired
    private InputRestValidator inputRestValidator;

    @Test
    @DisplayName("Is class Injectable")
    void isInjectable() {
        assertThat(inputRestValidator).isNotNull();
    }

    @Test
    @DisplayName("fraction parsing")
    void checkFractionParsing() {
        Optional<String> optional = inputRestValidator.validate("50/5");
        String expectedOutput = FractionToBigDecimalParser.class.getCanonicalName();

        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get())
                .isNotNull()
                .isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("fraction parsing (division by zero)")
    void checkFractionParsingDivideByZero() {
        Optional<String> optional = inputRestValidator.validate("50/0");
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    @DisplayName("integer number parsing")
    void checkIntegerNumberParsing() {
        Optional<String> optional = inputRestValidator.validate("1684");

        String expectedOutput = IntegerToBigDecimalParser.class.getCanonicalName();

        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get())
                .isNotNull()
                .isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("non hexadecimal string parsing")
    void checkStringParsing() {
        Optional<String> optional = inputRestValidator.validate("jkdfhlglkjsd");
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    @DisplayName("hexadecimal number parsing")
    void checkHexadecimalNumberParsing() {
        Optional<String> optional = inputRestValidator.validate("A50ff");
        String expectedOutput = HexadecimalToBigDecimalParser.class.getCanonicalName();

        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get())
                .isNotNull()
                .isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Check decimal number parsing")
    void checkDecimalNumberParsing() {
        Optional<String> optional = inputRestValidator.validate("12345.546");
        String expectedOutput = DecimalToBigDecimalParser.class.getCanonicalName();

        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get())
                .isNotNull()
                .isEqualTo(expectedOutput);
    }

}