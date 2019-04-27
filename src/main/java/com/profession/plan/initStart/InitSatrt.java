/**
 * @Title: InitSatrt.java
 * @Package com.profession.plan.initStart
 * @Description: 初始化启动类
 * @author 熊正胜
 * @date 2019年4月8日
 * @version V1.0
 */
package com.profession.plan.initStart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.profession.plan.component.CrawlData;
import com.profession.plan.component.CrawlUrl;

/**
 * @ClassName: InitSatrt
 * @Description: 初始化启动类
 * @author 熊正胜
 * @date 2019年4月8日
 *
 */
@Component
public class InitSatrt implements CommandLineRunner {

	@Autowired
	private CrawlData crawlData;
	
	@Autowired
	private CrawlUrl crawlUrl;
	
	@Override
	public void run(String... args) throws Exception {
		while (true) {
			crawlUrl.work();
			
			crawlData.work();
		}
	}

}
