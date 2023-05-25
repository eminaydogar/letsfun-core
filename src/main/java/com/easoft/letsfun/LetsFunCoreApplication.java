package com.easoft.letsfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.easoft.letsfun.cache.CacheManager;

//@Import({ SwaggerConfig.class })
@SpringBootApplication
public class LetsFunCoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(LetsFunCoreApplication.class, args);
	}

	@Bean
	public void load() {
		CacheManager.load();
	}

}
