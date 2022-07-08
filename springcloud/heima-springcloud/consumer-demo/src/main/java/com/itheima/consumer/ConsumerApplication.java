package com.itheima.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*@SpringBootApplication
@EnableDiscoveryClient //开启Eureka客户端发现功能
@EnableCircuitBreaker //开启熔断*/
@SpringCloudApplication  //一个注解包含了上面的三个注解。。
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced   //ribbon负载均衡注解。
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
