package com.example.demo;

import generated.ObjectFactory;
import generated.PlayerDetails;
import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableJms
public class TestJms1Application {

	public static void main(String[] args) {
		SpringApplication.run(TestJms1Application.class, args);
	}


	@Bean
	public MessageConverter hifJmsMessageConverter() {
		return new MyConverter(unmarshaller(), marshaller());
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
