package com.zhenhui.sparkler.service.bootstrap;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@MapperScan("com.zhenhui.sparkler.data.model.mapper")
@SpringBootApplication(scanBasePackages = {"com.zhenhui.sparkler"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}


