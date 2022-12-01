package com.ubivashka.openstack.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import com.ubivashka.openstack.api.util.ResourceUtil;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.ubivashka.openstack.api.controller"), @ComponentScan("com.ubivashka.openstack.api.configuration"), @ComponentScan("com.ubivashka.openstack.api.filter")})
public class Main {
    public static void main(String[] args) {
        ResourceUtil.createResource("application.properties");
        SpringApplication.run(Main.class, args);
    }
}
