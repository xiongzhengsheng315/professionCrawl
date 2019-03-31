/**
 * @Title: CrawlDataStart.java
 * @Package com.profession.data.crawl.professionCrawl.initStart
 * @Description: 爬虫数据处理类
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.initStart;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.profession.data.crawl.professionCrawl.entity.CrawlDetail;
import com.profession.data.crawl.professionCrawl.service.CrawlDetailService;

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

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlDetailService crawlDetailService;
	
	@Override
	public void run(String... args) throws Exception {
		while (true) {
			this.work();
		}
	}

	/**
	 * @Title: work
	 * @Description: 处理爬虫数据
	 * @return void 返回类型
	 * @throws
	 */
	private void work() {
		List<CrawlDetail> crawlDetails = crawlDetailService.listCrawlDetails();
		if(CollectionUtils.isEmpty(crawlDetails)) {
			logger.info("没有待处理的爬虫数据!");
			return;
		}
	}

}
