package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.lang.reflect.Constructor;
import java.util.Properties;

public class ConfigFactory {
    public ConfigFactory() {
    }

    public static ConfigService createConfigService(Properties properties) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.config.NacosConfigService");
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            ConfigService vendorImpl = (ConfigService)constructor.newInstance(properties);
            return vendorImpl;
        } catch (Throwable var4) {
            throw new NacosException(-400, var4);
        }
    }

    public static ConfigService createConfigService(String serverAddr) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
//        properties.put("namespace", namespace);
//        properties.put("username", username);
//        properties.put("password", password);
//        properties.put("group", group);

        return createConfigService(properties);
    }
}
