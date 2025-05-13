package com.gagansp1.myfirstsringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyfirstsringbootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MyfirstsringbootApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment);

	}





	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager( dbFactory);
	}


}




