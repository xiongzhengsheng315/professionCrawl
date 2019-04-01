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
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.profession.data.crawl.professionCrawl.constant.CrawlDetailConstant;
import com.profession.data.crawl.professionCrawl.crawlers.AbstractCrawlHandle;
import com.profession.data.crawl.professionCrawl.entity.CrawlDetail;
import com.profession.data.crawl.professionCrawl.entity.Work;
import com.profession.data.crawl.professionCrawl.service.CrawlDetailService;
import com.profession.data.crawl.professionCrawl.service.WorkService;
import com.profession.data.crawl.professionCrawl.util.ApplicationContextUtil;

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
	private WorkService workService;
	
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
		//爬取对应的数据
		for (CrawlDetail crawlDetail : crawlDetails) {
			int type = crawlDetail.getType();
			try {
				AbstractCrawlHandle crawlHandle = getCrawlHandleObject(type);
				if(crawlHandle == null) {
					logger.error("爬虫处理的类为null!");
				}
				Work work = crawlHandle.crawlDetailInfo(crawlDetail.getCrawlUrl());
				workService.saveWork(work);
				//变更爬虫详情记录
				crawlDetailService.updateCrawlDetail(crawlDetail.getId());
			} catch (Exception e) {
				logger.error("爬取{}数据失败!", crawlDetail.getCrawlUrl() ,e);
			}
		}
	}

	/**
	 * @Title: getCrawlHandleObject
	 * @Description: 获取不同渠道下的处理bean
	 * @param type 爬虫渠道类型
	 * @return AbstractCrawlHandle 返回类型
	 * @throws
	 */
	public AbstractCrawlHandle getCrawlHandleObject(int type){
		Map<Integer, String> map = CrawlDetailConstant.CrawlType.getHandleBeanNames();
		String handleBeanName = map.get(type);
		if(StringUtils.isEmpty(handleBeanName)){
			return null;
		}
		AbstractCrawlHandle crawlHandleBean = (AbstractCrawlHandle) ApplicationContextUtil.applicationContext.getBean(handleBeanName);
		return crawlHandleBean;
	}
}
