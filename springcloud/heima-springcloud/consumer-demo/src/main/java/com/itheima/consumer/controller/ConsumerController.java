package com.itheima.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    //访问：
//    @GetMapping("/{id}")
//    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
//    @HystrixCommand
//    public User queryById(@PathVariable Long id){
//        if (id == 1) {
//            throw new RuntimeException("太忙了");
//        }
//        String url = "http://localhost:9091/user/"+id;
//
//        //获取eureka中注册的user-service的实例       //IP地址可以变化，
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
//        ServiceInstance serviceInstance = serviceInstances.get(0);
//
//        url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;
//
////        String url = "http://user-service/user/" + id;
//
//        //return restTemplate.getForObject(url, String.class);
//        return restTemplate.getForObject(url, User.class);
//    }

    @GetMapping("/{id}")
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
    @HystrixCommand
    public String queryById(@PathVariable Long id){
        if (id == 1) {
            throw new RuntimeException("太忙了");
        }
//        String url = "http://localhost:9091/user/"+id;

        //获取eureka中注册的user-service的实例
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
//        ServiceInstance serviceInstance = serviceInstances.get(0);
//
//        url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String url = "http://user-service/user/" + id;

        //return restTemplate.getForObject(url, String.class);
        return restTemplate.getForObject(url, String.class);
    }

    public String queryByIdFallback(Long id){
        log.error("查询用户信息失败。id：{}", id);
        return "对不起，网络太拥挤了！";
    }

    public String defaultFallback(){

        String url = "http://user-service/user/8" ;



        String ret = "默认提示：对不起，网络太拥挤了！";
        return ret;
    }
}
