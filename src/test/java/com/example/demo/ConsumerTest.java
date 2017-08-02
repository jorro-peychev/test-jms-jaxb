package com.example.demo;

import static org.junit.Assert.*;

import generated.PlayerDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by georgi.peychev on 8/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {

  @Autowired
  private Consumer consumer;

  @Test
  public void receiveQueue() throws Exception {
    PlayerDetails playerDetails = new PlayerDetails();
    consumer.receiveQueue(playerDetails);
  }

}