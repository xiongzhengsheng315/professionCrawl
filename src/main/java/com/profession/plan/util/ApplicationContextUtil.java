/**
 * @Title: ApplicationContextUtil.java
 * @Package com.profession.plan.util
 * @Description: 获取spring容器上下文
 * @author 熊正胜
 * @date 2019年4月1日
 * @version V1.0
 */
package com.profession.plan.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationContextUtil
 * @Description: 获取spring容器上下文
 * @author 熊正胜
 * @date 2019年4月1日
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	public static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

}
