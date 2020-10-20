package com.bootcamp37.bc37consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Bc37consumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bc37consumeApplication.class, args);
                System.out.println("Client Jalan");
	}
        @Bean
        public RestTemplate getRestTemplate(){
            return new RestTemplate();
        }

}
