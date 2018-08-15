package com.company.user.configuration;

import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * User: zjt
 * DateTime: 2018/2/10 15:04
 *
 * mybatis配置：加载数据源等
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@ConfigurationProperties
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {

    private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

    //mybatis全局配置文件
    @Value("${mybatis.config-location}")
    private String configLocation;

    // 配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    // 配置类型别名
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Autowired
    private DataSource dataSource;

    /**
     * 提供SqlSeesion
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() {
        try {

            //设置数据源
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);

            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);

            //设置mybatis-config.xml配置文件位置
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            //添加分页插件、打印sql插件
            Interceptor[] plugins = new Interceptor[]{pageHelper(), sqlPrintInterceptor()};
            sessionFactoryBean.setPlugins(plugins);

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error", e);
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error", e);
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 事务管理
     * @return
     */
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 拦截执行的sql进行日志打印
     * @return
     */
    @Bean
    public SqlPrintInterceptor sqlPrintInterceptor() {
        return new SqlPrintInterceptor();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}