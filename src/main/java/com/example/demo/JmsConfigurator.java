package com.example.demo;

import generated.ObjectFactory;
import generated.PlayerDetails;
import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ErrorHandler;

/**
 * Created by georgi.peychev on 8/2/17.
 */
@Configuration
@EnableJms
public class JmsConfigurator {


  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
      DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory,
      ErrorHandler errorHandlerService) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    factory.setErrorHandler(errorHandlerService);
    factory.setMessageConverter(createJmsMessageConverter());
    factory.setSessionTransacted(Boolean.FALSE);
    return factory;
  }

  @Bean
  public MessageConverter createJmsMessageConverter() {
    return new JmsXmlConverter(unmarshaller(), marshaller());
  }


  @Bean
  public Marshaller marshaller() {
    final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setPackagesToScan(ObjectFactory.class.getPackage().getName());

    final Map<String, Object> map = new HashMap<>();
    map.put("jaxb.formatted.output", Boolean.FALSE);
    marshaller.setMarshallerProperties(map);
    return marshaller;
  }

  @Bean
  public Unmarshaller unmarshaller() {
    final Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
    unmarshaller.setPackagesToScan(ObjectFactory.class.getPackage().getName());
    unmarshaller.supports(PlayerDetails.class);
    return unmarshaller;
  }
}
