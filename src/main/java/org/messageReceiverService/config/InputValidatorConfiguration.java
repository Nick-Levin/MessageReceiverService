package org.messageReceiverService.config;

import org.messageReceiverService.validator.InputRestValidator;
import org.messageReceiverService.validator.InputRestValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InputValidatorConfiguration {

    @Bean
    public InputRestValidator inputRestValidator() {
        return new InputRestValidatorService();
    }

}
