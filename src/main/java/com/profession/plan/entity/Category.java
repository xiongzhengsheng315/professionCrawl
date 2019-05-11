/**
 * @Title: Category.java
 * @Package com.profession.plan.entity
 * @Description: 分类
 * @author 熊正胜
 * @date 2019年5月3日
 * @version V1.0
 */
package com.profession.plan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @ClassName: Category
 * @Description: 分类
 * @author 熊正胜
 * @date 2019年5月3日
 *
 */
public class Category implements Serializable {

	/**
	 * @Fields field:field:{todo}
	 */
	private static final long serialVersionUID = -6682741344344068716L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	private Long Id;
	
	/**
	 * 分类名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 层级
	 */
	@Column(name = "level")
	private Integer level;
	
	/**
	 * 父id
	 */
	@Column(name = "fid")
	private Long fid;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	
	/**
	 * 版本号
	 */
	@Column(name = "version")
	private Integer version;
}
