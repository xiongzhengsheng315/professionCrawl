/**
 * @Title: Area.java
 * @Package com.profession.data.crawl.professionCrawl.entity
 * @Description: 省市区类型
 * @author 熊正胜
 * @date 2019年4月5日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @ClassName: Area
 * @Description: 省市区类型
 * @author 熊正胜
 * @date 2019年4月5日
 *
 */
public class Area implements Serializable {

	/**
	 * @Fields field:field:{todo}
	 */
	private static final long serialVersionUID = 213420624973921998L;

	/**
	 * 区域id
	 */
	@Id
	@Column(name = "id")
	private Long id;
	
	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 简称
	 */
	@Column(name = "short_name")
	private String shortName;
	
	/**
	 * 父区域id
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
