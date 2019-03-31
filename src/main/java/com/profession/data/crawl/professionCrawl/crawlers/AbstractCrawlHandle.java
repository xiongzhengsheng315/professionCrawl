/**
 * @Title: AbstractCrawlHandle.java
 * @Package com.profession.data.crawl.professionCrawl.crawlers
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.crawlers;

import java.util.List;

import com.profession.data.crawl.professionCrawl.entity.CrawlProfessionConfig;

/**
 * @ClassName: AbstractCrawlHandle
 * @Description: 爬虫抽象处理类
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
public abstract class AbstractCrawlHandle {

	/**
	 * @Title: getCrawlDetailUrls
	 * @Description: 获取爬虫详情信息
	 * @param crawlProfessionConfigs
	 * @return List<String> 返回类型
	 * @throws
	 */
	public abstract List<String> getCrawlDetailUrls(List<CrawlProfessionConfig> crawlProfessionConfigs);
}
