package org.messageReceiverService.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InputRestValidatorService implements InputRestValidator {

    @Autowired
    private ValidationMap validationMap;

    @Override
    public Optional<String> validate(String numericValue) {
        return validationMap
                .getKeySet()
                .stream()
                .filter(key -> numericValue.matches(validationMap.get(key)))
                .findAny();
    }
}
