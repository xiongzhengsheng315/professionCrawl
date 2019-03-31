/**
 * @Title: WorkService.java
 * @Package com.profession.data.crawl.professionCrawl.service
 * @Description: 爬虫岗位业务接口
 * @author 熊正胜
 * @date 2019年3月20日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.service;

import com.profession.data.crawl.professionCrawl.entity.Work;

/**
 * @ClassName: WorkService
 * @Description: 爬虫岗位业务接口
 * @author 熊正胜
 * @date 2019年3月20日
 *
 */
public interface WorkService {

	/**
	 * @Title: saveWork
	 * @Description: 保存爬虫对应的数据
	 * @param work 岗位信息
	 * @param @return 参数
	 * @return Boolean 返回类型
	 * @throws
	 */
	public Boolean saveWork(Work work);
}
