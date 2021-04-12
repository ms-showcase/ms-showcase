package io.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CovidShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidShowcaseApplication.class, args);
    }
}
