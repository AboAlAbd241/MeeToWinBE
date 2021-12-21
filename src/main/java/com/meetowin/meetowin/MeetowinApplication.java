package com.meetowin.meetowin;

import com.meetowin.meetowin.security.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MeetowinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetowinApplication.class, args);
	}

}
