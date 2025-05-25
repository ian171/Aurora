package net.chen.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static Map<String, Object> config = new HashMap<>();
    private static Config instance = new Config();
    private Config() {

    }

    public static Config getInstance() {
//        if (config == null) {
//            ConfigLoader configLoader = new ConfigLoader();
//            configLoader.loadConfig();
//        }
        return instance;
    }

    public void setConfigValue(String key, Object value) {
        config.put(key, value);
    }

    public Object getConfigValue(String key) {
        return config.get(key);
    }
}
