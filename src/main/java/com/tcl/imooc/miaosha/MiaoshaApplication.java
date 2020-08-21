package com.tcl.imooc.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hundanli
 * @date 2020/8/21
 * @version 0.0.1
 */
@SpringBootApplication
@MapperScan(basePackages = "com.tcl.imooc.miaosha.*.mapper")
public class MiaoshaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaApplication.class, args);
    }

}
