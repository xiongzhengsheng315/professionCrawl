/**
 * @Title: WorkServiceImpl.java
 * @Package com.profession.plan.service.impl
 * @Description: 爬虫岗位业务接口实现类
 * @author 熊正胜
 * @date 2019年3月20日
 * @version V1.0
 */
package com.profession.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profession.plan.entity.Work;
import com.profession.plan.mapper.WorkMapper;
import com.profession.plan.service.WorkService;

/**
 * @ClassName: WorkServiceImpl
 * @Description: 爬虫岗位业务接口实现类
 * @author 熊正胜
 * @date 2019年3月20日
 *
 */
@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkMapper workMapper;
	
	/**
	 * @Title: saveWork
	 * @Description: 保存爬虫对应的数据
	 * @param work 岗位信息
	 * @param @return 参数
	 * @return Boolean 返回类型
	 * @throws
	 */
	@Override
	public Boolean saveWork(Work work) {
		int record = workMapper.insertSelective(work);
		return record > 0;
	}

}
