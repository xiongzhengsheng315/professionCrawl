/**
 * @Title: CategoryService.java
 * @Package com.profession.plan.service
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月3日
 * @version V1.0
 */
package com.profession.plan.service;

import java.util.List;

import com.profession.plan.entity.Category;

/**
 * @ClassName: CategoryService
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月3日
 *
 */
public interface CategoryService {

	/**
	 * @Title: batchSaveCategory
	 * @Description: 批量保存类目
	 * @param categorys
	 * @param @return 参数
	 * @return int 返回类型
	 * @throws
	 */
	public int batchSaveCategory(List<Category> categorys);
	
	/**
	 * @Title: saveCategory
	 * @Description: 保存类目
	 * @param category
	 * @param @return 参数
	 * @return Long 返回类型
	 * @throws
	 */
	public Long saveCategory(Category category);
	
	/**
	 * @Title: checkInitCategory
	 * @Description: 检查类目是否被初始化
	 * @param @return 参数
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkInitCategory();
}
