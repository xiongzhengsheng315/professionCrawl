/**
 * @Title: CrawlProfessionConfigService.java
 * @Package com.profession.data.crawl.professionCrawl.service
 * @Description: 爬虫配置业务接口
 * @author 熊正胜
 * @date 2019年3月24日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.service;

import java.util.List;

import com.profession.data.crawl.professionCrawl.entity.CrawlProfessionConfig;

/**
 * @ClassName: CrawlProfessionConfigService
 * @Description: 爬虫配置业务接口
 * @author 熊正胜
 * @date 2019年3月24日
 *
 */
public interface CrawlProfessionConfigService {

	/**
	 * @Title: listCrawlProfessionConfigs
	 * @Description: 查询待爬取的职业
	 * @param crawlStatus 爬虫状态
	 * @return List<CrawlProfessionConfig> 返回类型
	 * @throws
	 */
	public List<CrawlProfessionConfig> listCrawlProfessionConfigs(int crawlStatus);
	
	/**
	 * @Title: updateCrawlProfessionConfig
	 * @Description: 更新爬虫配置表
	 * @param crawlProfessionConfigs
	 * @param status 爬虫处理状态
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateCrawlProfessionConfig(List<CrawlProfessionConfig> crawlProfessionConfigs, Integer status);
}
