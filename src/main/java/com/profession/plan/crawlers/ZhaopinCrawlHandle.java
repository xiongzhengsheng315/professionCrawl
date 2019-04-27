/**
 * @Title: ZhaopinResponseHandle.java
 * @Package com.profession.plan.crawlers
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月29日
 * @version V1.0
 */
package com.profession.plan.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.profession.plan.entity.Area;
import com.profession.plan.entity.CrawlProfessionConfig;
import com.profession.plan.entity.Work;
import com.profession.plan.service.AreaService;
import com.profession.plan.util.DateUtil;

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
	
	@Autowired
	private AreaService areaService;
	
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
		if(StringUtils.isBlank(detailUrl)){
			logger.info("爬虫url:{}信息为空!", detailUrl);
			return null;
		}
		Work work = new Work();
		try {
			Document document = Jsoup.connect(detailUrl).ignoreContentType(true).get();
			Element element = document.body();
			parsePageInfo(element, work);
		} catch (IOException e) {
			logger.error("爬虫详情数据抓取失败!", e);
		}
		return work;
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
    
    /**
     * @Title: parsePageInfo
     * @Description: 解析页面内容
     * @param element 页面对象
     * @param work 参数
     * @return void 返回类型
     * @throws
     */
    private void parsePageInfo(Element element, Work work) {
    	Element rootElement = element.getElementById("root");
    	try {
			String jobName = rootElement.getElementsByClass("summary-plane__title").get(0).text();
			String salary = rootElement.getElementsByClass("summary-plane__salary").get(0).text();
			Elements elements = rootElement.getElementsByClass("highlights__content-item");
			List<String> highlights = new ArrayList<>();
			if(elements != null && elements.size() > 0) {
				for (Element highlight : elements) {
					highlights.add(highlight.text());
				}
			}
			Elements jobElements = rootElement.getElementsByClass("describtion__detail-content");
			List<String> jobDutys = new ArrayList<>();
			for (Element jobElement : jobElements) {
				jobDutys.add(jobElement.text());
			}
			String workPlace = rootElement.getElementsByClass("job-address__content-text").get(0).text();
			String companyName = rootElement.getElementsByClass("company__title").get(0).text();
			List<Element> planeInfoElements = rootElement.getElementsByClass("summary-plane__info").get(0).getElementsByTag("li");
			//
			String cityName = planeInfoElements.get(0).getElementsByTag("a").get(0).text();
			Area cityArea = areaService.getArea(cityName);
			Long cityId = 0L;
			if(cityArea != null) {
				cityId = cityArea.getId();
				work.setCityId(cityId);
			}
			Elements regionElements = planeInfoElements.get(0).getElementsByTag("span");
			if(regionElements != null && regionElements.size() > 0) {
				String regionName = regionElements.get(0).text();
				Area regionArea = areaService.getArea(cityId, regionName);
				if(regionArea != null) {
					work.setRegionId(regionArea.getId());
				}
			}
			//
			String academicRequire = planeInfoElements.get(2).text();
			
			work.setJobName(jobName);
			work.setJobRequeire(StringUtils.join(jobDutys, ""));
			work.setJobBrightSpot(StringUtils.join(highlights, ""));
			work.setSalary(salary);
			work.setWorkPlace(workPlace);
			work.setCompanyName(companyName);
			work.setCompanyAddress(workPlace);
			work.setAcademicRequire(academicRequire);
			//处理公司信息
			String companyUrl = element.getElementsByClass("company__home-page").get(0).text();
			work.setCompanyWebsite(companyUrl);
			String conpanyIntroUrl = rootElement.getElementsByClass("company__page-site").get(0).attr("href");
			handleCompanyInfo(conpanyIntroUrl, work);
			//
			Date now = DateUtil.getNow();
			work.setCreateTime(now);
			work.setUpdateTime(now);
			work.setVersion(0);
		} catch (Exception e) {
			logger.error("收集智联详情数据异常!", e);
		}
	}

    /**
     * @Title: handleCompanyInfo
     * @Description: 获取公司详情
     * @param conpanyIntroUrl 公司介绍url信息
     * @param work
     * @param @return 参数
     * @throws IOException 
     * @return void 返回类型
     * @throws
     */
	private void handleCompanyInfo(String conpanyIntroUrl, Work work) {
		try {
			Document document = Jsoup.connect(conpanyIntroUrl).ignoreContentType(true).get();
			Element element = document.body();
			String companyNature = element.getElementsByClass("overview__detail").get(0).getElementsByTag("span").get(0).text();
			work.setCompanyNature(companyNature);
			String companyWebsite = element.getElementsByClass("overview__url").get(0).text();
			work.setCompanyWebsite(companyWebsite);
			String companyIntroduce = element.getElementsByClass("company-show__content__description").get(0).text();
			work.setCompanyIntroduce(companyIntroduce);
		} catch (Exception e) {
		}
	}
    
    
}
