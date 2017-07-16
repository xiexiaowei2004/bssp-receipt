package com.powerbridge.bssp.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by simon.xie on 2017/6/30 0030.
 */
@Configuration
public class BeanDataSourceConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private MybatisPlusProperties mybatisProperties;
    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    @Autowired(required = false)
    private Interceptor[] interceptors;
    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    public void setBaseDataSourceConfig(AtomikosDataSourceBean xaDataSource) throws SQLException {
        xaDataSource.setMinPoolSize(environment.getProperty("spring.datasource.bssp.minPoolSize", Integer.class));
        xaDataSource.setMaxPoolSize(environment.getProperty("spring.datasource.bssp.maxPoolSize", Integer.class));
        xaDataSource.setMaxLifetime(environment.getProperty("spring.datasource.bssp.maxLifetime", Integer.class));
        xaDataSource.setBorrowConnectionTimeout(environment.getProperty("spring.datasource.bssp.borrowConnectionTimeout", Integer.class));
        xaDataSource.setLoginTimeout(environment.getProperty("spring.datasource.bssp.loginTimeout", Integer.class));
        xaDataSource.setMaintenanceInterval(environment.getProperty("spring.datasource.bssp.maintenanceInterval", Integer.class));
        xaDataSource.setMaxIdleTime(environment.getProperty("spring.datasource.bssp.maxIdleTime", Integer.class));
        xaDataSource.setTestQuery(environment.getProperty("spring.datasource.bssp.bsspQuery"));
    }

    public MybatisSqlSessionFactoryBean getMybatisSqlSessionFactoryBean(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.mybatisProperties.getConfigLocation())) {
            mybatisSqlSessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation()));
        }
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisSqlSessionFactoryBean.setPlugins(this.interceptors);
        }
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisSqlSessionFactoryBean.setConfiguration(mc);
        if (this.databaseIdProvider != null) {
            mybatisSqlSessionFactoryBean.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
            mybatisSqlSessionFactoryBean.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
            mybatisSqlSessionFactoryBean.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
        }
        return mybatisSqlSessionFactoryBean;
    }

}
