package com.aquirozc.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.aquirozc.forohub.rsa2048.RSAKeyPair;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyPair.class)
public class ForoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

}
