/**
 * @Title: CrawlProfessionConfig.java
 * @Package com.profession.plan.entity
 * @Description: 爬虫配置表
 * @author 熊正胜
 * @date 2019年3月24日
 * @version V1.0
 */
package com.profession.plan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: CrawlProfessionConfig
 * @Description: 爬虫配置表
 * @author 熊正胜
 * @date 2019年3月24日
 *
 */
@Table(name = "crawl_profession_config")
public class CrawlProfessionConfig implements Serializable {

	/**
	 * @Fields field:field:{todo}
	 */
	private static final long serialVersionUID = -2484295609404456760L;

	
	/**
	 * 爬虫id
	 */
	@Id
	@Column(name = "id")
	private Long id;
	
	
	/**
	 * 爬虫职位
	 */
	@Column(name = "job_name")
	private String jobName;
	
	/**
	 * 行业名称
	 */
	@Column(name = "trade_name")
	private String tradeName;
	
	/**
	 * 是否删除  false:不删除;true:删除
	 */
	@Column(name = "is_delete")
	private Boolean delete;
	
	/**
	 * 是否被爬取  0:未爬取;1:爬取中;2:爬取完成
	 */
	@Column(name = "status")
	private Integer status;
	
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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