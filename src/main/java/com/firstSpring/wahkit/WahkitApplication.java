package com.firstSpring.wahkit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@MapperScan("com.firstSpring.wahkit.dao")
public class WahkitApplication {

	public static void main(String[] args) {
		SpringApplication.run(WahkitApplication.class, args);
	}
}
