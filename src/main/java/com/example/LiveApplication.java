package com.example;


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.base.BaseRepositoryFactoryBean;

@EnableJpaRepositories(basePackages = {"com.example"},repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
//@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableScheduling
@SpringBootApplication
public class LiveApplication {

	public static void main(String[] args) {
//		SpringApplication.run(LiveApplication.class, args);
		System.out.print("hello my live LOK!!");
		 SpringApplication.run(LiveApplication.class,args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:???");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
}}
