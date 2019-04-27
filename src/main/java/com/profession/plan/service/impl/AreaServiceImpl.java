/**
 * @Title: AreaServiceImpl.java
 * @Package com.profession.plan.service.impl
 * @Description: 区域业务接口实现
 * @author 熊正胜
 * @date 2019年4月5日
 * @version V1.0
 */
package com.profession.plan.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profession.plan.entity.Area;
import com.profession.plan.mapper.AreaMapper;
import com.profession.plan.service.AreaService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName: AreaServiceImpl
 * @Description: 区域业务接口实现
 * @author 熊正胜
 * @date 2019年4月5日
 *
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;
	
	/**
	 * @Title: getArea
	 * @Description: 获取区域对象
	 * @param cityName 城市名称
	 * @param @return 参数
	 * @return Area 返回类型
	 * @throws
	 */
	@Override
	public Area getArea(String cityName) {
		Example example = new Example(Area.class);
		Criteria criteria = example.createCriteria();
		criteria.andLike("name", "%" + cityName + "%");
		criteria.andNotEqualTo("parentId", 0);
		List<Area> areas = areaMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(areas)) {
			return areas.get(0);
		}
		return null;
	}

	/**
	 * @Title: getArea
	 * @Description: 获取区域对象
	 * @param cityId 城市id
	 * @param regionName 区域名称
	 * @param @return 参数
	 * @return Area 返回类型
	 * @throws
	 */
	@Override
	public Area getArea(Long cityId, String regionName) {
		Example example = new Example(Area.class);
		Criteria criteria = example.createCriteria();
		criteria.andLike("name", regionName);
		criteria.andEqualTo("parentId", cityId);
		List<Area> areas = areaMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(areas)) {
			return areas.get(0);
		}
		return null;
	}

}
