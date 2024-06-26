package com.ohh.linksaucegateway;

import com.ohh.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDubbo
@Service
public class LinksauceGatewayApplication {

    @DubboReference
    private RpcDemoService rpcDemoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LinksauceGatewayApplication.class, args);
        LinksauceGatewayApplication application = context.getBean(LinksauceGatewayApplication.class);
        String result = application.doSayHello("world");
        System.out.println("result = " + result);
    }

    public String doSayHello(String name) {
        return rpcDemoService.sayHello(name);
    }


}
