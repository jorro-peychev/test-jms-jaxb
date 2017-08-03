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
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ErrorHandler;

@SpringBootApplication
public class TestJms1Application {

	public static void main(String[] args) {
		SpringApplication.run(TestJms1Application.class, args);
	}

}
