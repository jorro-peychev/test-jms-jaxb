package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * Error handling service to receive internal JMS errors
 */
@Service
public class ErrorHandlerService implements ErrorHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerService.class);

  @Override
  public void handleError(Throwable t) {
    LOGGER.error("========>>>Error in listener", t);
  }
}
