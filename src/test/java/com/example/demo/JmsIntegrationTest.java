package com.example.demo;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
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
public class JmsIntegrationTest {


  @Autowired
  private Sender sender;

  @Before
  public void setup() {
    sender.sendMessage();
  }

  @Test
  public void consumeMessageTest() throws Exception {

  }
}
