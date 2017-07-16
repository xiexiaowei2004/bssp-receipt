package com.powerbridge.bssp.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.powerbridge.bssp.Constant.*;

/**
 * Created by Administrator on 2017/6/24 0024.
 */
@Configuration
/*@PropertySource("classpath:config/application-dev.properties")*/
@MapperScan(basePackages = {BSSP_SAS_MAPPER_SCAN, BSSP_SAS_CMB_MAPPER_SCAN, BSSP_SAS_PASS_MAPPER_SCAN}, sqlSessionTemplateRef = "bsspSasSqlSessionTemplate")
public class BsspSasDataSourceConfig extends BeanDataSourceConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource bsspSasDataSource() throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(environment.getProperty("spring.datasource.bssp-sas.url"));
        mysqlXaDataSource.setPassword(environment.getProperty("spring.datasource.bssp-sas.password"));
        mysqlXaDataSource.setUser(environment.getProperty("spring.datasource.bssp-sas.username"));
        /*mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);*/

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("bsspSasDataSource");
        setBaseDataSourceConfig(xaDataSource);
        return xaDataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean bsspSasSqlSessionFactoryBean(@Qualifier("bsspSasDataSource") DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = getMybatisSqlSessionFactoryBean(dataSource);
        return mybatisSqlSessionFactoryBean;

    }

    @Bean
    public SqlSessionTemplate bsspSasSqlSessionTemplate(@Qualifier("bsspSasSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
