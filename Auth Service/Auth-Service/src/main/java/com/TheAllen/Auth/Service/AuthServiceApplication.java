package com.TheAllen.Auth.Service;

import com.TheAllen.Auth.Service.messaging.UserEventStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableMongoAuditing
@EnableBinding(UserEventStream.class)
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
