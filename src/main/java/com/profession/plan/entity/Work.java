/**
 * @Title: Work.java
 * @Package com.profession.plan.entity
 * @Description: 岗位实体
 * @author 熊正胜
 * @date 2019年3月20日
 * @version V1.0
 */
package com.profession.plan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Work
 * @Description: 岗位实体
 * @author 熊正胜
 * @date 2019年3月20日
 *
 */
@Table(name = "work")
public class Work implements Serializable {

	/**
	 * @Fields field:field:{todo}
	 */
	private static final long serialVersionUID = 5121911158412440740L;

	/**
	 * 爬虫id
	 */
	@Id
	@Column(name = "id")
	private Long id;
	
	/**
	 * 爬虫id
	 */
	@Column(name = "crawl_id")
	private Long crawlId;
	
	/**
	 * 岗位名称
	 */
	@Column(name = "job_name")
	private String jobName;
	
	/**
	 * 岗位职责
	 */
	@Column(name = "job_duty")
	private String jobDuty;
	
	/**
	 * 岗位要求
	 */
	@Column(name = "job_requeire")
	private String jobRequeire;
	
	/**
	 * 技能要求
	 */
	@Column(name = "skill_require")
	private String skillRequire;
	
	/**
	 * 相关专业
	 */
	@Column(name = "related_major")
	private String relatedMajor;
	
	/**
	 * 学历要求
	 */
	@Column(name = "academic_require")
	private String academicRequire;
	
	/**
	 * 优先项
	 */
	@Column(name = "priority_item")
	private String priorityItem;
	
	/**
	 * 加分项
	 */
	@Column(name = "bonus_point")
	private String bonusPoint;
	
	/**
	 * 工作地点
	 */
	@Column(name = "work_place")
	private String workPlace;
	
	/**
	 * 城市id
	 */
	@Column(name = "city_id")
	private Long cityId;
	
	/**
	 * 区域id
	 */
	@Column(name = "region_id")
	private Long regionId;
	
	/**
	 * 公司名称
	 */
	@Column(name = "company_name")
	private String companyName;
	
	/**
	 * 公司地址
	 */
	@Column(name = "company_address")
	private String companyAddress;
	
	/**
	 * 薪资待遇
	 */
	@Column(name = "salary")
	private String salary;
	
	/**
	 * 职位亮点
	 */
	@Column(name = "job_bright_spot")
	private String jobBrightSpot;
	
	/**
	 * 公司介绍
	 */
	@Column(name = "company_introduce")
	private String companyIntroduce;
	
	/**
	 * 公司网址
	 */
	@Column(name = "company_website")
	private String companyWebsite;
	
	/**
	 * 公司性质
	 */
	@Column(name = "company_nature")
	private String companyNature;
	
	/**
	 * 知识点
	 */
	@Column(name = "knowledge_point")
	private String knowledgePoint;
	
	/**
	 * 状态  0:待审核;1:已审核;2:审核失败;
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

	public Long getCrawlId() {
		return crawlId;
	}

	public void setCrawlId(Long crawlId) {
		this.crawlId = crawlId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDuty() {
		return jobDuty;
	}

	public void setJobDuty(String jobDuty) {
		this.jobDuty = jobDuty;
	}

	public String getJobRequeire() {
		return jobRequeire;
	}

	public void setJobRequeire(String jobRequeire) {
		this.jobRequeire = jobRequeire;
	}

	public String getSkillRequire() {
		return skillRequire;
	}

	public void setSkillRequire(String skillRequire) {
		this.skillRequire = skillRequire;
	}

	public String getRelatedMajor() {
		return relatedMajor;
	}

	public void setRelatedMajor(String relatedMajor) {
		this.relatedMajor = relatedMajor;
	}

	public String getAcademicRequire() {
		return academicRequire;
	}

	public void setAcademicRequire(String academicRequire) {
		this.academicRequire = academicRequire;
	}

	public String getPriorityItem() {
		return priorityItem;
	}

	public void setPriorityItem(String priorityItem) {
		this.priorityItem = priorityItem;
	}

	public String getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(String bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJobBrightSpot() {
		return jobBrightSpot;
	}

	public void setJobBrightSpot(String jobBrightSpot) {
		this.jobBrightSpot = jobBrightSpot;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getKnowledgePoint() {
		return knowledgePoint;
	}

	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
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