package cn.youfull.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.youfull.ui.mapper")
public class UiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class,args);
    }
}
