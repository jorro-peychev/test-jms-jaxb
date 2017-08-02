package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by georgi.peychev on 8/2/17.
 */
@Component
public class ProducerCommandLineRunner implements CommandLineRunner {

  private Sender sender;

  @Autowired
  public ProducerCommandLineRunner(Sender sender) {
    this.sender = sender;
  }

  @Override
  public void run(String... args) throws Exception {
    sender.sendMessage();
  }
}
