package com.wooriat.admin.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityConfig {

    @Bean
    public VelocityEngine velocityEngine(){
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.addProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.addProperty("resource.loader", "file");
        velocityEngine.addProperty("input.encoding", "UTF-8");
        velocityEngine.addProperty("output.encoding", "UTF-8");

        return velocityEngine;
    }
}
