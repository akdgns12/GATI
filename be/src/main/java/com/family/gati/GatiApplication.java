package com.family.gati;

import com.family.gati.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableConfigurationProperties({
		AppProperties.class
})
public class GatiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatiApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}
