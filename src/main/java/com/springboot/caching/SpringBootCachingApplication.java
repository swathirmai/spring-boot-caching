package com.springboot.caching;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.springboot.caching.scheduler.CacheScheduler;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("com.springboot.caching.*")
public class SpringBootCachingApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootCachingApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootCachingApplication.class, args);
		
		try {
			CacheScheduler cacheScheduler = ctx.getBean(CacheScheduler.class);
			cacheScheduler.setCachingData();
		} catch (Exception e) {
			log.error("Error in SpringBootCachingApplication - shutting down : " + e.getMessage());
			ctx.close();
		}
	}

}
