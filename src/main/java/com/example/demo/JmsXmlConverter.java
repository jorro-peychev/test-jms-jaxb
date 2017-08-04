package com.example.demo;

import generated.PlayerDetails;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 * Created by georgi.peychev on 8/2/17.
 */
public class JmsXmlConverter extends SimpleMessageConverter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JmsXmlConverter.class);

  private Unmarshaller unmarshaller;
  private Marshaller marshaller;

  @Autowired
  public JmsXmlConverter(Unmarshaller unmarshaller, Marshaller marshaller) {
    this.unmarshaller = unmarshaller;
    this.marshaller = marshaller;
  }

  @Override
  public Message toMessage(Object object, Session session) throws JMSException {
    if (object instanceof PlayerDetails) {
      try {

        final StringWriter writer = new StringWriter();
        marshaller.marshal(object, new StreamResult(writer));
        final String payload = writer.toString();
        LOGGER.debug("Outgoing message payload: [{}]", payload);
        return createMessageForString(payload, session);
      } catch (IOException e) {
        throw new MessageConversionException("Cant convert to Telegram:" + e.getMessage(),
            e); // NOSONAR
      }
    }
    return super.toMessage(object, session);
  }

  @Override
  public Object fromMessage(Message message) throws JMSException {
    if (message instanceof TextMessage) {
      String payload = ((TextMessage) message).getText();
      LOGGER.debug("Incomming message payload: [{}]", payload);
      try {
        Object object = unmarshaller.unmarshal(new StreamSource(new StringReader(payload)));
        return object;
      } catch (IOException e) {
        throw new MessageConversionException("Cant convert to Telegram:" + e.getMessage(),
            e); // NOSONAR
      }

    }
    return super.fromMessage(message);
  }
}
