/**
 * @Title: DateUtil.java
 * @Package com.profession.plan.util
 * @Description: 日期公共类
 * @author 熊正胜
 * @date 2019年3月31日
 * @version V1.0
 */
package com.profession.plan.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 日期公共类
 * @author 熊正胜
 * @date 2019年3月31日
 *
 */
public class DateUtil {

	/**
	 * @Title: getNow
	 * @Description: 获取当前时间
	 * @return Date 返回类型
	 * @throws
	 */
	public static Date getNow(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
}
