package com.study.me.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fanqie
 * Created on 2020.09.18
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class Config {

    private final ConfigProperties configProperties;

    public Config(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

//    /**
//     *
//     * @return default bean
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public ConfigProperties configProperties() {
//        return new ConfigProperties();
//    }

    public String printConfigProperties() {
        return configProperties.toString();
    }
}
