/**
 * @Title: ZhaopinResponseHandle.java
 * @Package com.profession.data.crawl.professionCrawl.crawlers
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月29日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.profession.data.crawl.professionCrawl.entity.CrawlProfessionConfig;
import com.profession.data.crawl.professionCrawl.entity.Work;

/**
 * @ClassName: ZhaopinCrawlHandle
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月29日
 *
 */
@Component("zhaopinCrawlHandle")
public class ZhaopinCrawlHandle extends AbstractCrawlHandle {

	Logger logger = LoggerFactory.getLogger(getClass());
		
	/**
	 * 循环退出表示
	 */
	private volatile Boolean loop_flag = true;
	
	private final int PAGE_SIZE = 90;
	
	/**
	 * @Title: getCrawlDetailUrls
	 * @Description: 获取智联招聘爬虫详情信息
	 * @param crawlProfessionConfigs
	 * @return List<String> 返回类型
	 * @throws
	 */
	@Override
	public List<String> getCrawlDetailUrls(List<CrawlProfessionConfig> crawlProfessionConfigs){
		int page = 1;
		List<String> crawlUrls = new ArrayList<>();
		Map<String, String> param = new HashMap<>();
		param.put("pageSize", String.valueOf(PAGE_SIZE));
		param.put("salary", "0,0");
		param.put("workExperience", "-1");
		param.put("education", "-1");
		param.put("companyType", "-1");
		param.put("employmentType", "-1");
		param.put("jobWelfareTag", "-1");
		param.put("kt", "3");
		param.put("at", "9113c7830a364e02a12c8d5fe5280181");
		param.put("rt", "3dd4ea4b75ad442ebbeceedcf7611d8a");
		param.put("_v", "0.72721213");
		param.put("userCode", "1030791836");
		param.put("x-zp-page-request-id", "2d836e6116dd4a93b720b9f7f921c8b3-1553917586499-724378");
		while(true){
			for (CrawlProfessionConfig crawlProfessionConfig : crawlProfessionConfigs) {
				String keyWord = crawlProfessionConfig.getJobName();
				param.put("kw", keyWord);
				param.put("start", String.valueOf((page-1)*90));
				Document document = null;
				try {
					document = Jsoup.connect("https://fe-api.zhaopin.com/c/i/sou").ignoreContentType(true).data(param).get();
					String content = document.body().html();
					List<String> urls = handleCrawlUrl(content);
					if(CollectionUtils.isNotEmpty(urls)){
						crawlUrls.addAll(urls);
					}
				} catch (IOException e) {
					logger.error("获取待爬取的地址信息错误!", e);
				}
	            
			}
			page++;
			if(loop_flag){
				break;
			}
			
			//休息3s
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				logger.error("抓取爬虫数据数据休眠异常!", e);
			}
		}
		return crawlUrls;
	};
	
	/**
	 * @Title: crawlDetailInfo
	 * @Description: 爬取智联招聘详情信息
	 * @param detailUrl 爬虫url信息
	 * @return Work 返回类型
	 * @throws
	 */
	@Override
	public Work crawlDetailInfo(String detailUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Title: handleCrawlUrl
	 * @Description: 获取智联招聘详情页url
	 * @param content 参数
	 * @return List<String> 返回类型
	 * @throws
	 */
    @SuppressWarnings("rawtypes")
	private List<String> handleCrawlUrl(String content) {
    	if(StringUtils.isBlank(content)){
    		return null;
    	}
    	List<String> crawlDetailUrls = new ArrayList<>();
		Map map = JSON.parseObject(content, Map.class);
		String data = String.valueOf(map.get("data"));
		if(StringUtils.isNotBlank(data)){
			Map dataObj = JSON.parseObject(data, Map.class);
			String dataResultStr = String.valueOf(dataObj.get("results"));
			List<Map> dataLists = JSON.parseArray(dataResultStr, Map.class);
			if(CollectionUtils.isEmpty(dataLists)){
				return crawlDetailUrls;
			}
			for (Map result : dataLists) {
				String detailUrl = String.valueOf(result.get("positionURL"));
				if(StringUtils.isNotBlank(detailUrl)){
					crawlDetailUrls.add(detailUrl);
				}
			}
			
			if(dataLists.size() < PAGE_SIZE){
				loop_flag = true;
			}
		}
		return crawlDetailUrls;
	}
}
