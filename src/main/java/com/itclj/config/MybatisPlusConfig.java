package com.itclj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.itclj.dao.mapper")
public class MybatisPlusConfig {
}
