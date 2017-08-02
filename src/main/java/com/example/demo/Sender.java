package com.example.demo;

import generated.PlayerDetails;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by martin.bonev on 7/19/17.
 */
@Component
public class Sender implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate template;

    public Sender(JmsTemplate template) {
        this.template = template;
    }

    public void sendMessage() {
        logger.info("Sending email message.");
        //Email email = new Email("me@test.invalid", "Hello (" + LocalDateTime.now() + ")!");
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setName("test");
        template.convertAndSend("elisa-mailbox", playerDetails);
        logger.info("===>Sending email message.");
    }

    @Override
    public void run(String... args) throws Exception {
        sendMessage();
    }
}
