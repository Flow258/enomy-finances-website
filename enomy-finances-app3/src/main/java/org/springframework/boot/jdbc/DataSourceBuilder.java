package org.springframework.boot.jdbc;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * A utility class for building a DataSource instance.
 */
public class DataSourceBuilder {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    
    public static DataSourceBuilder create() {
        return new DataSourceBuilder();
    }

    public DataSourceBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DataSourceBuilder username(String username) {
        this.username = username;
        return this;
    }

    public DataSourceBuilder password(String password) {
        this.password = password;
        return this;
    }

    public DataSourceBuilder driverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public DataSource build() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(20);
        return dataSource;
    }
}
