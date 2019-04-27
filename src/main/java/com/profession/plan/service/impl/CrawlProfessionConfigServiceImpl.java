/**
 * @Title: CrawlProfessionConfigServiceImpl.java
 * @Package com.profession.plan.service.impl
 * @Description: 爬虫配置业务接口实现类
 * @author 熊正胜
 * @date 2019年3月24日
 * @version V1.0
 */
package com.profession.plan.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profession.plan.entity.CrawlProfessionConfig;
import com.profession.plan.mapper.CrawlProfessionConfigMapper;
import com.profession.plan.service.CrawlProfessionConfigService;
import com.profession.plan.util.DateUtil;

/**
 * @ClassName: CrawlProfessionConfigServiceImpl
 * @Description: 爬虫配置业务接口实现类
 * @author 熊正胜
 * @date 2019年3月24日
 *
 */
@Service
public class CrawlProfessionConfigServiceImpl implements CrawlProfessionConfigService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlProfessionConfigMapper crawlProfessionConfigMapper;
	
	/**
	 * @Title: listCrawlProfessionConfigs
	 * @Description: 查询待爬取的职业
	 * @param crawlStatus 爬虫状态
	 * @return List<CrawlProfessionConfig> 返回类型
	 * @throws
	 */
	public List<CrawlProfessionConfig> listCrawlProfessionConfigs(int crawlStatus) {
		CrawlProfessionConfig param = new CrawlProfessionConfig();
		param.setStatus(crawlStatus);
		return crawlProfessionConfigMapper.select(param);
	}
	
	/**
	 * @Title: updateCrawlProfessionConfig
	 * @Description: 更新爬虫配置表
	 * @param crawlProfessionConfigs
	 * @param status 爬虫处理状态
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateCrawlProfessionConfig(List<CrawlProfessionConfig> crawlProfessionConfigs, Integer status) {
		if(CollectionUtils.isEmpty(crawlProfessionConfigs)) {
			return false;
		}
		for (CrawlProfessionConfig crawlProfessionConfig : crawlProfessionConfigs) {
			long id = crawlProfessionConfig.getId();
			CrawlProfessionConfig param = new CrawlProfessionConfig();
			param.setId(id);
			param.setUpdateTime(DateUtil.getNow());
			param.setStatus(status);
			crawlProfessionConfigMapper.updateByPrimaryKeySelective(param);
		}
		return true;
	}
}
