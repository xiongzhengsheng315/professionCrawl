/**
 * @Title: CrawlUrl.java
 * @Package com.profession.plan.component.CrawlUrl
 * @Description: 爬虫详情url
 * @author 熊正胜
 * @date 2019年3月24日
 * @version V1.0
 */
package com.profession.plan.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.profession.plan.constant.CrawlDetailConstant;
import com.profession.plan.constant.CrawlProfessionConfigConstant;
import com.profession.plan.crawlers.DajieCrawlHandle;
import com.profession.plan.crawlers.Job51CrawlHandle;
import com.profession.plan.crawlers.ZhaopinCrawlHandle;
import com.profession.plan.entity.CrawlDetail;
import com.profession.plan.entity.CrawlProfessionConfig;
import com.profession.plan.service.CrawlDetailService;
import com.profession.plan.service.CrawlProfessionConfigService;
import com.profession.plan.util.DateUtil;

/**
 * @ClassName: CrawlUrl
 * @Description: 爬虫详情url
 * @author 熊正胜
 * @date 2019年3月24日
 *
 */
@Component
public class CrawlUrl {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlProfessionConfigService crawlProfessionConfigService;
	
	@Autowired
	private ZhaopinCrawlHandle zhaopinCrawlHandle;
	
	@Autowired
	private Job51CrawlHandle job51CrawlHandle;
	
	@Autowired
	private CrawlDetailService crawlDetailService;
	
	@Autowired
	private DajieCrawlHandle dajieCrawlHandle;

	/**
	 * @Title: work
	 * @Description: 收集带爬虫的url信息
	 * @return void 返回类型
	 * @throws
	 */
	public void work(){
		List<CrawlProfessionConfig> crawlProfessionConfigs = crawlProfessionConfigService.
				listCrawlProfessionConfigs(CrawlProfessionConfigConstant.CrawlStatus.PEND.getStatus());
		if(CollectionUtils.isEmpty(crawlProfessionConfigs)){
			return;
		}
		logger.info("待爬取的数据:{}", JSON.toJSONString(crawlProfessionConfigs));
		//变更爬虫配置状态为已完成
		crawlProfessionConfigService.updateCrawlProfessionConfig(crawlProfessionConfigs, CrawlProfessionConfigConstant.CrawlStatus.PEND.getStatus());
		//处理智联
		this.handleZhilian(crawlProfessionConfigs);
		//处理前程无忧
		this.handle51Job(crawlProfessionConfigs);
//		//处理大街网
//		this.handleDaJie(crawlProfessionConfigs);
		logger.info("爬虫url信息抓取完毕!");
		//变更爬虫配置状态为已完成
		crawlProfessionConfigService.updateCrawlProfessionConfig(crawlProfessionConfigs, CrawlProfessionConfigConstant.CrawlStatus.FINISH.getStatus());
	}
	
	/**
	 * @Title: handleZhilian
	 * @Description: 获取智联招聘要抓取的详情url
	 * @param crawlProfessionConfigs 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void handleZhilian(List<CrawlProfessionConfig> crawlProfessionConfigs){
		List<String> detailUrls = zhaopinCrawlHandle.getCrawlDetailUrls(crawlProfessionConfigs);
		this.saveCrawlDetailUrls(detailUrls, CrawlDetailConstant.CrawlType.ZHILIAN.getType());
	}
	
	/**
	 * @Title: handle51Job
	 * @Description: 获取前程无忧要抓取的详情url
	 * @param crawlProfessionConfigs 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void handle51Job(List<CrawlProfessionConfig> crawlProfessionConfigs) {
		List<String> detailUrls = job51CrawlHandle.getCrawlDetailUrls(crawlProfessionConfigs);
		this.saveCrawlDetailUrls(detailUrls, CrawlDetailConstant.CrawlType.QIANCHENG.getType());
	}
	
	/**
	 * @Title: handleDaJie
	 * @Description: 获取大街网要抓取的详情url
	 * @param crawlProfessionConfigs 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void handleDaJie(List<CrawlProfessionConfig> crawlProfessionConfigs) {
		List<String> detailUrls = dajieCrawlHandle.getCrawlDetailUrls(crawlProfessionConfigs);
		this.saveCrawlDetailUrls(detailUrls, CrawlDetailConstant.CrawlType.DAJIE.getType());
	}
	
	/**
	 * @Title: saveCrawlDetailUrls
	 * @Description: 保存爬虫详情url信息
	 * @param detailUrls 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void saveCrawlDetailUrls(List<String> detailUrls, Integer type){
		List<CrawlDetail> crawlDetails = new ArrayList<>();
		CrawlDetail crawlDetail = null;
		Date date = DateUtil.getNow();
		for (String url : detailUrls) {
			crawlDetail = new CrawlDetail();
			crawlDetail.setCrawlUrl(url);
			crawlDetail.setCrawl(false);
			crawlDetail.setType(type);
			crawlDetail.setCreateTime(date);
			crawlDetail.setUpdateTime(date);
			crawlDetail.setVersion(0);
			crawlDetails.add(crawlDetail);
		}
		crawlDetailService.saveCrawlDetail(crawlDetails);
	}
}
