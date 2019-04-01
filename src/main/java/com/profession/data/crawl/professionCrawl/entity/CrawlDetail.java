/**
 * @Title: CrawlDetail.java
 * @Package com.profession.data.crawl.professionCrawl.entity
 * @Description: 爬虫详情表
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.data.crawl.professionCrawl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @ClassName: CrawlDetail
 * @Description: 爬虫详情表
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
public class CrawlDetail implements Serializable {

	/**
	 * @Fields field:field:{todo}
	 */
	private static final long serialVersionUID = 1368072303133215405L;

	/**
	 * 爬虫id
	 */
	@Id
	@Column(name = "id")
	private Long id;
	
	/**
	 * 爬虫url
	 */
	@Column(name = "crawl_url")
	private String crawlUrl;
	
	/**
	 * 类型  1:智联;2:51job;3:大街;
	 */
	@Column(name = "type")
	private Integer type;
	
	/**
	 * 是否爬取过 true:已经爬取过;false:未爬取
	 */
	@Column(name = "is_crawl")
	private Boolean crawl;
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

	public String getCrawlUrl() {
		return crawlUrl;
	}

	public void setCrawlUrl(String crawlUrl) {
		this.crawlUrl = crawlUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getCrawl() {
		return crawl;
	}

	public void setCrawl(Boolean crawl) {
		this.crawl = crawl;
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
