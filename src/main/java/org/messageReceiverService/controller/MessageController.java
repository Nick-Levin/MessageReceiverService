package org.messageReceiverService.controller;

import org.messageReceiverService.model.Message;
import org.messageReceiverService.parser.BigDecimalParser;
import org.messageReceiverService.validator.InputRestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    InputRestValidator inputRestValidator;

    @Autowired
    BlockingQueue<Message> blockingQueue;

    @PostMapping
    public ResponseEntity<String> processMessage(
            @RequestParam("message") String message
    ) throws Exception {
        Optional<String> optional = inputRestValidator.validate(message);
        if(optional.isPresent()) {
            BigDecimalParser parser = (BigDecimalParser) Class.forName(optional.get()).getConstructor().newInstance();
            BigDecimal number = parser.parse(message);
            blockingQueue.put(new Message(number, message));
            return ResponseEntity.ok(number + "");
        } else {
            return ResponseEntity.badRequest().body("invalid message");
        }
    }

}
