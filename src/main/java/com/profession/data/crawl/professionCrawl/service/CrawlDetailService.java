/**
 * @Title: ICrawlDetailService.java
 * @Package com.profession.data.crawl.professionCrawl.service
 * @Description: 爬虫详情业务接口
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.service;

import java.util.List;

import com.profession.data.crawl.professionCrawl.entity.CrawlDetail;

/**
 * @ClassName: ICrawlDetailService
 * @Description: 爬虫详情业务接口
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
public interface CrawlDetailService {

	/**
	 * @Title: saveCrawlDetail
	 * @Description: 保存爬虫详情数据
	 * @param crawlDetails 参数
	 * @return void 返回类型
	 * @throws
	 */
	public void saveCrawlDetail(List<CrawlDetail> crawlDetails);
}
