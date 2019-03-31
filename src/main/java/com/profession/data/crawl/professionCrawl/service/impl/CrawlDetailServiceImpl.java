/**
 * @Title: CrawlDetailServiceImpl.java
 * @Package com.profession.data.crawl.professionCrawl.service.impl
 * @Description: 爬虫详情业务接口实现
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profession.data.crawl.professionCrawl.entity.CrawlDetail;
import com.profession.data.crawl.professionCrawl.mapper.CrawlDetailMapper;
import com.profession.data.crawl.professionCrawl.service.CrawlDetailService;

/**
 * @ClassName: CrawlDetailServiceImpl
 * @Description: 爬虫详情业务接口实现
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
@Service
public class CrawlDetailServiceImpl implements CrawlDetailService {

	@Autowired
	private CrawlDetailMapper crawlDetailMapper;
	
	/**
	 * @Title: saveCrawlDetail
	 * @Description: 保存爬虫详情数据
	 * @param crawlDetails 参数
	 * @return void 返回类型
	 * @throws
	 */
	public void saveCrawlDetail(List<CrawlDetail> crawlDetails){
		if(CollectionUtils.isEmpty(crawlDetails)){
			return;
		}
		for (CrawlDetail crawlDetail : crawlDetails) {
			crawlDetailMapper.insert(crawlDetail);
		}
	}
}
