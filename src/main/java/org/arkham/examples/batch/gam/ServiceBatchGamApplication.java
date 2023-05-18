package org.arkham.examples.batch.gam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceBatchGamApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceBatchGamApplication.class, args);
	}

}
