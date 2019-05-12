/**
 * @Title: CrawlCategory.java
 * @Package com.profession.plan.component
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月12日
 * @version V1.0
 */
package com.profession.plan.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @ClassName: CrawlCategory
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月12日
 *
 */
@Component
public class CrawlCategory {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Title: getAllCategory
	 * @Description: 获取所有的分类
	 * @param @return 参数
	 * @return Map<String, List<String>> 返回类型
	 * @throws
	 */
	public Map<String, List<String>> getAllCategory() {
		Map<String, List<String>> result = new HashMap<>(10);
		Document document = null;
		try {
			document = Jsoup.connect("https://www.zhaopin.com/").ignoreContentType(true).get();
			Element element = document.body();
			handleCategory(element, result);
		} catch (IOException e) {
			logger.error("获取待爬取的类目信息错误!", e);
		}
		return result;
	}

	/**
	 * @Title: handleCategory
	 * @Description: TODO
	 * @param element
	 * @param result 参数
	 * @return void 返回类型
	 * @throws
	 */
	private void handleCategory(Element element, Map<String, List<String>> result) {
		Elements elements = element.getElementsByClass("zp-jobNavigater__pop--container");
		if(elements == null || elements.size() == 0) {
			return;
		}
		for (Element ele : elements) {
			String parentCategoryName = ele.getElementsByClass("zp-jobNavigater__pop--title").get(0).text();
			Elements childElements = ele.getElementsByClass("zp-jobNavigater__pop--href");
			List<String> childCateNames = Lists.newArrayList();
			if(childElements != null && childElements.size() > 0) {
				for (Element childElement : childElements) {
					String childCategoryName = childElement.text();
					childCateNames.add(childCategoryName);
				}
			}
			result.put(parentCategoryName, childCateNames);
		}
	}
}