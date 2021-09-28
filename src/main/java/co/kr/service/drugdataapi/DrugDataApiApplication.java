package co.kr.service.drugdataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
public class DrugDataApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugDataApiApplication.class, args);
    }

}
