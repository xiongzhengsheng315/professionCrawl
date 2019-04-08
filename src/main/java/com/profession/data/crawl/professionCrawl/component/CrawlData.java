/**
 * @Title: CrawlData.java
 * @Package com.profession.data.crawl.professionCrawl.initStart
 * @Description: 爬虫数据处理类
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.component;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profession.data.crawl.professionCrawl.constant.CrawlDetailConstant;
import com.profession.data.crawl.professionCrawl.crawlers.AbstractCrawlHandle;
import com.profession.data.crawl.professionCrawl.entity.CrawlDetail;
import com.profession.data.crawl.professionCrawl.entity.Work;
import com.profession.data.crawl.professionCrawl.service.CrawlDetailService;
import com.profession.data.crawl.professionCrawl.service.WorkService;
import com.profession.data.crawl.professionCrawl.util.ApplicationContextUtil;

/**
 * @ClassName: CrawlData
 * @Description: 爬虫数据处理类
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
@Component
public class CrawlData {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private CrawlDetailService crawlDetailService;

	/**
	 * @Title: work
	 * @Description: 处理爬虫数据
	 * @return void 返回类型
	 * @throws
	 */
	public void work() {
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
				work.setCrawlId(crawlDetail.getId());
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
