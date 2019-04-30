package com.profession.plan.configuration;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @ClassName: MyBatisMapperScannerConfig   
 * @Description: mybatis 包扫描配置  
 * @author: xiongzhengsheng
 * @date: 2019年3月11日 下午23:49:08       
 * @Copyright: 2018 xiongzhengsheng Inc. All rights reserved.
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	/**
	 * @Title: mapperScannerConfigurer   
	 * @Description: mapperScanner扫描注入   
	 * @param: @return      
	 * @return: MapperScannerConfigurer      
	 * @throws
	 */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.profession.plan.mapper");
        Properties properties = new Properties();
        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。
        properties.setProperty("mappers", Mapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
