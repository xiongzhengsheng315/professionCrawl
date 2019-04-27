/**
 * @Title: CrawlDetailServiceImpl.java
 * @Package com.profession.plan.service.impl
 * @Description: 爬虫详情业务接口实现
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.plan.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profession.plan.entity.CrawlDetail;
import com.profession.plan.mapper.CrawlDetailMapper;
import com.profession.plan.service.CrawlDetailService;
import com.profession.plan.util.DateUtil;

import tk.mybatis.mapper.entity.Example;

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
	
	/**
	 * @Title: listCrawlDetails
	 * @Description: 获取待爬虫的详情数据
	 * @return List<CrawlDetail> 返回类型
	 * @throws
	 */
	public List<CrawlDetail> listCrawlDetails() {
		Example example = new Example(CrawlDetail.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("crawl", false);
		List<CrawlDetail> crawlDetails = crawlDetailMapper.selectByExample(example);
		return crawlDetails;
	}
	
	/**
	 * @Title: updateCrawlDetail
	 * @Description: 更新爬虫详情状态
	 * @param id 主键
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateCrawlDetail(Long id) {
		CrawlDetail param = new CrawlDetail();
		param.setId(id);
		param.setCrawl(true);
		param.setUpdateTime(DateUtil.getNow());
		int record = crawlDetailMapper.updateByPrimaryKeySelective(param);
		return record > 0;
	}
}
