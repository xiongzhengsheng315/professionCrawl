/**
 * @Title: CategoryLevelConstant.java
 * @Package com.profession.plan.constant
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月12日
 * @version V1.0
 */
package com.profession.plan.constant;

/**
 * @ClassName: CategoryLevelConstant
 * @Description: TODO
 * @author 熊正胜
 * @date 2019年5月12日
 *
 */
public enum CategoryLevelConstant {
	onelevel(0, "第一级"),
	secondlevel(1, "第二级");
	
	private Integer level;
	
	private String desc;
	
	private CategoryLevelConstant(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}

	public Integer getLevel() {
		return level;
	}

	public String getDesc() {
		return desc;
	}
}
