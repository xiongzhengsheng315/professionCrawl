/**
 * @Title: CrawlDataStart.java
 * @Package com.profession.data.crawl.professionCrawl.initStart
 * @Description: 爬虫数据处理类
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.initStart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CrawlDataStart
 * @Description: 爬虫数据处理类
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
@Component
@Order(2)
public class CrawlDataStart implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("92372397429342947239");
	}

}
