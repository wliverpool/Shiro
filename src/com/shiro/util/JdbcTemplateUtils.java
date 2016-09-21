package com.shiro.util;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * jdbctemplate帮助�?shiro未与spring集成时使�?
 * @author 吴福�?
 *
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://182.119.175.130:3307/liverpool");
        ds.setUsername("root");
        ds.setPassword("wfmhbbwt");

        return new JdbcTemplate(ds);
    }

}