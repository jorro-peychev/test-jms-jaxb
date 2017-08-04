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
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate template;

    public Sender(JmsTemplate template) {
        this.template = template;
    }

    public void sendMessage() {
        logger.info("Sending PlayerDetails message.");
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setName("test_________________________-1");
        template.convertAndSend("elisa-mailbox", playerDetails);
        logger.info("===>Sending PlayerDetails message.");
    }

    public void sendWrongMessage() {
        logger.info("Sending email message.");
        //Email email = new Email("me@test.invalid", "Hello (" + LocalDateTime.now() + ")!");
        Email email = new Email();
        email.setSubject("test");
        template.convertAndSend("elisa-mailbox", email);
        logger.info("===>Sending email message.");
    }

    class Email {
        private String subject;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}
