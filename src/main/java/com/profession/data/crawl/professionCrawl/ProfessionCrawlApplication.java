 package com.profession.data.crawl.professionCrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProfessionCrawlApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProfessionCrawlApplication.class, args);
	}

}
