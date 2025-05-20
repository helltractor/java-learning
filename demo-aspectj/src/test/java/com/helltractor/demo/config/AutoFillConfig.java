package com.helltractor.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.helltractor.demo.AutoFillTest;
import com.helltractor.demo.service.IBuy;

/**
 * 配置类，配置 Spring Bean 扫描的基础路径和代理方式
 */
@Configuration

// 配置扫描的基础路径
@ComponentScan(basePackageClasses = {IBuy.class, AutoFillTest.class})

// 配置代理方式
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AutoFillConfig {
}
