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
import javax.persistence.Table;

/**
 * @ClassName: Category
 * @Description: 分类
 * @author 熊正胜
 * @date 2019年5月3日
 *
 */
@Table(name = "category")
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
	 * 启用状态
	 */
	@Column(name = "enable")
	private Boolean enable;
	
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
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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
