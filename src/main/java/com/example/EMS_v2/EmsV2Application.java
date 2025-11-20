package com.example.EMS_v2;

import com.example.EMS_v2.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class EmsV2Application {

	public static void main(String[] args) {
		SpringApplication.run(EmsV2Application.class, args);
	}

}
