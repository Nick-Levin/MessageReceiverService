package org.messageReceiverService.validator;

import java.util.Optional;

public interface InputRestValidator {

    Optional<String> validate(String numericValue);

}
