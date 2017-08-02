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
public class MyConverter extends SimpleMessageConverter {
  private static final Logger LOGGER = LoggerFactory.getLogger(MyConverter.class);

  private Unmarshaller unmarshaller;
  private Marshaller marshaller;

  @Autowired
  public MyConverter(Unmarshaller unmarshaller, Marshaller marshaller) {
    this.unmarshaller = unmarshaller;
    this.marshaller = marshaller;
  }

  @Override
  public Message toMessage(Object object, Session session) throws JMSException {
    if (object instanceof PlayerDetails) {
      try {
//        Telegram telegram = (Telegram) object;
//        if (TelegramType.ERROR.equals(telegram.getTelegramType())) {
//          ErrorTelegramType error = telegram.getPayload().getError();
//          LOGGER.info(OUTGOING_ERROR_LOGENTRY, telegram.getTelegramType(), telegram.getId(),
//              telegram.getTimestamp().toString(), error.getReferenceId(), error.getErrorCode(),
//              error.getErrorDescription());
//        } else {
//          LOGGER.info(OUTGOING_LOGENTRY, telegram.getTelegramType(), telegram.getId(),
//              (telegram.getTimestamp() != null) ? telegram.getTimestamp().toString() : "null");
//        }
        final StringWriter writer = new StringWriter();
        marshaller.marshal(object, new StreamResult(writer));
        final String payload = writer.toString();
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Outgoing message payload: [{}]", payload);
        }
        return createMessageForString(payload, session);
      } catch (IOException e) {
        throw new MessageConversionException("Cant convert to Telegram:" + e.getMessage(), e); // NOSONAR
      }
    }
    return super.toMessage(object, session);
  }

  @Override
  public Object fromMessage(Message message) throws JMSException {
    if (message instanceof TextMessage) {
      String payload = extractStringFromMessage((TextMessage) message);
      //if (null != payload && payload.contains("http://www.knapp.com/schemas/rewe_domain")) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Incomming message payload: [{}]", payload);
        }
        try {
          Object object = unmarshaller.unmarshal(new StreamSource(new StringReader(payload)));
//          if (object instanceof Telegram) {
//            Telegram telegram = (Telegram) object;
//            if (TelegramType.ERROR.equals(telegram.getTelegramType())) {
//              ErrorTelegramType error = telegram.getPayload().getError();
//              LOGGER.info(INCOMING_ERROR_LOGENTRY, telegram.getTelegramType(), telegram.getId(),
//                  telegram.getTimestamp().toString(), error.getReferenceId(), error.getErrorCode(),
//                  error.getErrorDescription());
//            } else {
//              LOGGER.info(INCOMING_LOGENTRY, telegram.getTelegramType(), telegram.getId(),
//                  telegram.getTimestamp().toString());
//            }
//          }
          return object;
        } catch (IOException e) {
          throw new MessageConversionException("Cant convert to Telegram:" + e.getMessage(), e); // NOSONAR
        }

      }
    //}
    return super.fromMessage(message);
  }

  @Override
  public TextMessage createMessageForString(String text, Session session) throws JMSException {
    // Only called from Unittests
    return super.createMessageForString(text, session);
  }

  @Override
  public String extractStringFromMessage(TextMessage message) throws JMSException {
    // Only called from Unittests
    return super.extractStringFromMessage(message);
  }
}
