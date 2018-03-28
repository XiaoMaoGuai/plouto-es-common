package plouto.es.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by huwenxuan on 2018/3/27.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "plouto.es.common")
public class SpringBootRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunner.class, args);
    }

}