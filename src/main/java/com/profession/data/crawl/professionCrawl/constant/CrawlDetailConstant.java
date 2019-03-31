/**
 * @Title: CrawlDetailConstant.java
 * @Package com.profession.data.crawl.professionCrawl.constant
 * @Description: 爬虫详情业务常量
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.constant;

/**
 * @ClassName: CrawlDetailConstant
 * @Description: 爬虫详情业务常量
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
public class CrawlDetailConstant {

	/**
	 * @ClassName: CrawlStatus
	 * @Description: 爬虫详情类型
	 * @author 熊正胜
	 * @date 2019年3月24日
	 *
	 */
	public enum CrawlType{
		ZHILIAN("智联", 1),
		QIANCHENG("前程无忧", 2),
		DAJIE("大街网", 3);
		
		private String desc;
		
		private Integer type;

		private CrawlType(String desc, Integer type) {
			this.desc = desc;
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public Integer getType() {
			return type;
		}
	}
}
