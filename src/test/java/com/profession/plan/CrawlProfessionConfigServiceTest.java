package com.profession.plan;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.profession.plan.entity.CrawlProfessionConfig;
import com.profession.plan.service.CrawlProfessionConfigService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlProfessionConfigServiceTest {

	@Autowired
	private CrawlProfessionConfigService crawlProfessionConfigService;
	
	@Test
	public void contextLoads() {
		System.out.println("====================================");
		List<CrawlProfessionConfig> O = crawlProfessionConfigService.listCrawlProfessionConfigs(0);
		System.out.println("====================================");
		
		System.out.println(JSON.toJSONString(O));
	}

}
