/**
 * @Title: CrawlDetailConstant.java
 * @Package com.profession.plan.constant.constant
 * @Description: 爬虫详情业务常量
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.plan.constant;

import java.util.HashMap;
import java.util.Map;

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
		ZHILIAN("智联", 1, "zhaopinCrawlHandle"),
		QIANCHENG("前程无忧", 2, "job51CrawlHandle"),
		DAJIE("大街网", 3, "dajieCrawlHandle");
		
		private String desc;
		
		private Integer type;
		
		private String handleBeanName;

		private CrawlType(String desc, Integer type, String handleBeanName) {
			this.desc = desc;
			this.type = type;
			this.handleBeanName = handleBeanName;
		}

		public String getDesc() {
			return desc;
		}

		public Integer getType() {
			return type;
		}

		public String getHandleBeanName() {
			return handleBeanName;
		}
		
		/**
		 * @Title: getHandleBeanNames
		 * @Description: 加载handlebean集合
		 * @return Map<Integer,String> 返回类型
		 * @throws
		 */
		public static Map<Integer, String> getHandleBeanNames(){
			Map<Integer, String> map = new HashMap<>();
			for (CrawlType type : CrawlType.values()) {
				map.put(type.getType(), type.getHandleBeanName());
			}
			return map;
		}
	}
}
