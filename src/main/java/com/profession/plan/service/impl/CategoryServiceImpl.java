/**
 * @Title: CategoryServiceImpl.java
 * @Package com.profession.plan.service.impl
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月11日
 * @version V1.0
 */
package com.profession.plan.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profession.plan.entity.Category;
import com.profession.plan.mapper.CategoryMapper;
import com.profession.plan.service.CategoryService;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月11日
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Title: batchSaveCategory
	 * @Description: 批量保存类目
	 * @param categorys
	 * @param @return 参数
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public int batchSaveCategory(List<Category> categorys) {
		if(CollectionUtils.isEmpty(categorys)) {
			logger.info("要保存的类目信息为空!");
			return 0;
		}
		int record = 0;
		for (Category category : categorys) {
			record += categoryMapper.insert(category);
		}
		if(record != categorys.size()) {
			throw new RuntimeException();
		}
		return record;
	}

	/**
	 * @Title: saveCategory
	 * @Description: 保存类目
	 * @param category
	 * @param @return 参数
	 * @return Long 返回类型
	 * @throws
	 */
	public Long saveCategory(Category category) {
		categoryMapper.insert(category);
		return category.getId();
	}
	
	/**
	 * @Title: checkInitCategory
	 * @Description: 检查类目是否被初始化
	 * @param @return 参数
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkInitCategory() {
		List<Category> categorys = categoryMapper.selectAll();
		return CollectionUtils.isEmpty(categorys);
	}
}
