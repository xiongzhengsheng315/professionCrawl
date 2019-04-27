package com.profession.plan.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: MyBatisConfig   
 * @Description: myibatis配置文件  
 * @author: xiongzhengsheng
 * @date: 2018年3月11日 下午23:45:26       
 * @Copyright: 2019 xiongzhengsheng Inc. All rights reserved.
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {
	
	@Value("${spring.datasource.url}")  
    private String dbUrl;
	
    @Value("${spring.datasource.username}")  
    private String username;
    
    @Value("${spring.datasource.password}")  
    private String password;

    @Value("${spring.datasource.driver-class-name}")  
    private String driverClassName;
	
    private static final Logger log = LoggerFactory.getLogger(MyBatisConfig.class);

    private String typeAliasesPackage = "com/profession/plan/entity";
    private String mapperLocations = "classpath*:mapper/*.xml";

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
	@Bean
	@Primary
	public DataSource dataSource(){
		//@Primary 注解作用是当程序选择dataSource时选择被注解的这个
		DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);  
        datasource.setDriverClassName(driverClassName);
		return datasource;
	}

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setTypeAliasesPackage(typeAliasesPackage);
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        // 添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(mapperLocations));
            return bean.getObject();
        } catch (Exception e) {
            log.error("初始化mybatis异常,异常原因{}", e);
            throw new RuntimeException(e);
        }
    }
}
