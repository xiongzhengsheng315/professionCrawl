/**
 * @Title: Job51CrawlHandle.java
 * @Package com.profession.data.crawl.professionCrawl.crawlers
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.crawlers;

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
import org.springframework.stereotype.Component;

import com.profession.data.crawl.professionCrawl.entity.CrawlProfessionConfig;
import com.profession.data.crawl.professionCrawl.entity.Work;
import com.profession.data.crawl.professionCrawl.util.DateUtil;

/**
 * @ClassName: Job51CrawlHandle
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
@Component("job51CrawlHandle")
public class Job51CrawlHandle extends AbstractCrawlHandle {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 循环退出表示
	 */
	private volatile Boolean loop_flag = true;
	
	/**
	 * @Title: getCrawlDetailUrls
	 * @Description: 获取前程无忧爬虫详情信息
	 * @param crawlProfessionConfigs
	 * @return List<String> 返回类型
	 * @throws
	 */
	@Override
	public List<String> getCrawlDetailUrls(List<CrawlProfessionConfig> crawlProfessionConfigs) {
		int page = 1;
		List<String> crawlUrls = new ArrayList<>();
		Map<String, String> param = new HashMap<>();
		param.put("lang", "c");
		param.put("postchannel", "0000");
		param.put("workyear", "99");
		param.put("cotype", "99");
		param.put("degreefrom", "99");
		param.put("jobterm", "99");
		param.put("companysize", "99");
		param.put("providesalary", "99");
		param.put("lonlat", "0,0");
		param.put("radius", "-1");
		param.put("ord_field", "0");
		param.put("confirmdate", "9");
		param.put("dibiaoid", "0");
		param.put("specialarea", "00");
		while(true){
			for (CrawlProfessionConfig crawlProfessionConfig : crawlProfessionConfigs) {
				String keyWord = crawlProfessionConfig.getJobName();
				param.put("kw", keyWord);
				param.put("start", String.valueOf((page-1)*90));
				Document document = null;
				try {
					document = Jsoup.connect("https://search.51job.com/list/000000,000000,0000,00,9,99,java,2,"+page+".html").ignoreContentType(true).data(param).get();
					Element content = document.body();
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
	}

	/**
	 * @Title: crawlDetailInfo
	 * @Description: 爬取前程无忧详情信息
	 * @param detailUrl 爬虫url信息
	 * @return Work 返回类型
	 * @throws
	 */
	@Override
	public Work crawlDetailInfo(String detailUrl) {
		if(StringUtils.isBlank(detailUrl)) {
			logger.info("爬虫url:{}为空!", detailUrl);
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
	 * @Description: 获取51job招聘详情页url
	 * @param content 参数
	 * @return List<String> 返回类型
	 * @throws
	 */
    private List<String> handleCrawlUrl(Element content) {
    	if(content == null){
    		return null;
    	}
    	List<String> crawlDetailUrls = new ArrayList<>();
    	Element result = content.getElementById("resultList");
    	Elements elements = result.getElementsByClass("el");
    	if(elements != null && elements.size() > 0){
    		for (Element element : elements) {
    			if(elements.indexOf(element) == 0) continue;
    			String detailUrl = element.getElementsByTag("a").get(0).attr("href");
    			crawlDetailUrls.add(detailUrl);
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
    	//
    	Element headerElement = element.getElementsByClass("tHeader").get(0);
    	String jobName = headerElement.getElementsByTag("h1").get(0).text();
    	String salary = headerElement.getElementsByTag("strong").get(0).text();
    	//取城市信息和经验要求
    	String info = headerElement.getElementsByClass("msg ltype").get(0).text();
    	if(StringUtils.isNotBlank(info)) {
    		info = info.replaceAll("&nbsp;", "").replaceAll(" ", "");
    		String[] infoArr = info.split("\\|");
    		
    	}
    	
    	Date now = DateUtil.getNow();
    	work.setCreateTime(now);
    	work.setUpdateTime(now);
    	work.setVersion(0);
    }
}
