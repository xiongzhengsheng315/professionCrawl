/**
 * @Title: InitSatrt.java
 * @Package com.profession.plan.initStart
 * @Description: 初始化启动类
 * @author 熊正胜
 * @date 2019年4月8日
 * @version V1.0
 */
package com.profession.plan.initStart;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.profession.plan.component.CrawlCategory;
import com.profession.plan.component.CrawlData;
import com.profession.plan.component.CrawlUrl;
import com.profession.plan.constant.CategoryLevelConstant;
import com.profession.plan.entity.Category;
import com.profession.plan.service.CategoryService;
import com.profession.plan.util.DateUtil;

/**
 * @ClassName: InitSatrt
 * @Description: 初始化启动类
 * @author 熊正胜
 * @date 2019年4月8日
 *
 */
@Component
public class InitSatrt implements CommandLineRunner {

	@Autowired
	private CrawlData crawlData;
	
	@Autowired
	private CrawlUrl crawlUrl;
	
	@Autowired
	private CrawlCategory crawlCategory;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public void run(String... args) throws Exception {
		//
		boolean flag = categoryService.checkInitCategory();
		if(flag) {
			Map<String, List<String>> categoryMap = crawlCategory.getAllCategory();
			List<Category> categorys = Lists.newArrayList();
			Date date = DateUtil.getNow();
			for (Map.Entry<String, List<String>> entry : categoryMap.entrySet()) {
				Category category = new Category();
				category.setEnable(true);
				category.setFid(0L);
				category.setLevel(CategoryLevelConstant.onelevel.getLevel());
				category.setName(entry.getKey());
				category.setCreateTime(date);
				category.setUpdateTime(date);
				category.setVersion(1);
				Long parentId = categoryService.saveCategory(category);
				for (String categoryName : entry.getValue()) {
					Category childCategory = new Category();
					childCategory.setEnable(true);
					childCategory.setFid(parentId);
					childCategory.setLevel(CategoryLevelConstant.secondlevel.getLevel());
					childCategory.setName(categoryName);
					childCategory.setCreateTime(date);
					childCategory.setUpdateTime(date);
					childCategory.setVersion(1);
					categorys.add(childCategory);
				}
			}
			categoryService.batchSaveCategory(categorys);
		}
		
		while (true) {
			crawlUrl.work();
			
			crawlData.work();
		}
	}

}
