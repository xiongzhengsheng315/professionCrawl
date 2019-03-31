/**
 * @Title: CrawlProfessionConfigConstant.java
 * @Package com.profession.data.crawl.professionCrawl.constant
 * @Description: 爬虫配置枚举
 * @author 熊正胜
 * @date 2019年3月24日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.constant;

/**
 * @ClassName: CrawlProfessionConfigConstant
 * @Description: 爬虫配置枚举
 * @author 熊正胜
 * @date 2019年3月24日
 *
 */
public class CrawlProfessionConfigConstant {

	/**
	 * @ClassName: CrawlStatus
	 * @Description: TODO
	 * @author 熊正胜
	 * @date 2019年3月24日
	 *
	 */
	public enum CrawlStatus{
		PEND("待爬虫", 0),
		IN_PROCESS("爬虫中", 1),
		FINISH("爬虫完成", 2);
		
		private String desc;
		
		private Integer status;

		private CrawlStatus(String desc, Integer status) {
			this.desc = desc;
			this.status = status;
		}

		public String getDesc() {
			return desc;
		}

		public Integer getStatus() {
			return status;
		}
	}
	
	public enum CrawlLevel{
		
	}
}
