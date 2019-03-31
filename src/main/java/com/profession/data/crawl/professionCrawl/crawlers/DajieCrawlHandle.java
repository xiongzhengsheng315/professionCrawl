/**
 * @Title: DajieCrawlHandle.java
 * @Package com.profession.data.crawl.professionCrawl.crawlers
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月31日
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
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.profession.data.crawl.professionCrawl.entity.CrawlProfessionConfig;
import com.profession.data.crawl.professionCrawl.entity.Work;

/**
 * @ClassName: DajieCrawlHandle
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
@Component("dajieCrawlHandle")
public class DajieCrawlHandle extends AbstractCrawlHandle {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 循环退出表示
	 */
	private volatile Boolean loop_flag = true;
	
	private final int PAGE_SIZE = 30;
	
	@Override
	public List<String> getCrawlDetailUrls(List<CrawlProfessionConfig> crawlProfessionConfigs) {
		List<String> crawlUrls = new ArrayList<>();
//		int page = 1;
//		Map<String, String> param = new HashMap<>();
//		param.put("order", "0");
//		param.put("ajax", "1");
//		param.put("city", "");
//		param.put("recruitType", "");
//		param.put("salary", "");
//		param.put("experience", "");
//		param.put("positionFunction", "");
//		param.put("_CSRFToken", "");
//		while(true){
//			for (CrawlProfessionConfig crawlProfessionConfig : crawlProfessionConfigs) {
//				String keyWord = crawlProfessionConfig.getJobName();
//				param.put("page", String.valueOf(page));
//				param.put("keyword", keyWord);
//				Document document = null;
//				try {
//					Connection conn = Jsoup.connect("https://so.dajie.com/job/ajax/search/filter");
//					conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//					conn.header("Accept-Encoding", "gzip, deflate, sdch");
//					conn.header("Accept-Language", "zh-CN,zh;q=0.8");
//					conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//					conn.header("authority", "so.dajie.com");
//					conn.header("path", "/job/ajax/search/filter?keyword=java&order=0&city=&recruitType=&salary=&experience=&page=1&positionFunction=&_CSRFToken=&ajax=1");
//					conn.header("scheme", "https");
//					conn.header("cookie", "DJ_UVID=MTU1Mzg0MzMwODU1NzMzMzAz; _ga=GA1.2.1820052474.1553843308; DJ_RF=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DipCt4GWXLiCHQC4QmbqJfHTHSKnJBrjDwWKu1WBa9SI3pDx_OMea6SgYHYv6rbhH%26wd%3D%26eqid%3Dfeb04f3100057d64000000025ca057c2; DJ_EU=http%3A%2F%2Fwww.dajie.com%2Faccount%2Ffeedback; _gid=GA1.2.48957187.1554012109; Hm_lvt_6822a51ffa95d58bbe562e877f743b4f=1553843308,1553847259,1554012109,1554038996; _ssytip=1554038998619; SO_COOKIE_V2=2a2bgU7aEPiIBvE1F2nj5RMRGZh57u9+cYbha2G8PaJyFEXoSzvRqUOnkD7kEnOZ7sz84Cefub86kVUHSu16zy5N8AgOw9xxticO; Hm_lpvt_6822a51ffa95d58bbe562e877f743b4f=1554039090; _gat_gtag_UA_117102476_1=1; _close_autoreg=1554039092592; _close_autoreg_num=4");
//					document = conn.ignoreContentType(true).data(param).get();
//					String content = document.body().html();
//					List<String> urls = handleCrawlUrl(content);
//					if(CollectionUtils.isNotEmpty(urls)){
//						crawlUrls.addAll(urls);
//					}
//				} catch (IOException e) {
//					logger.error("获取待爬取的地址信息错误!", e);
//				}
//	            
//			}
//			page++;
//			if(loop_flag){
//				break;
//			}
//			
//			//休息3s
//			try {
//				TimeUnit.SECONDS.sleep(3);
//			} catch (InterruptedException e) {
//				logger.error("抓取爬虫数据数据休眠异常!", e);
//			}
//		}
		return crawlUrls;
	}

	/**
	 * @Title: crawlDetailInfo
	 * @Description: 爬取大街网详情信息
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
			String dataResultStr = String.valueOf(dataObj.get("list"));
			List<Map> dataLists = JSON.parseArray(dataResultStr, Map.class);
			if(CollectionUtils.isEmpty(dataLists)){
				return crawlDetailUrls;
			}
			for (Map result : dataLists) {
				String detailUrl = String.valueOf(result.get("liHref"));
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
