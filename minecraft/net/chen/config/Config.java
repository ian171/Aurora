package net.chen.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static Map<String, Object> config = new HashMap<>();
    public Config() {

    }
    public void setConfigValue(String key, Object value) {
        config.put(key, value);
    }

    public Object getConfigValue(String key) {
        return config.get(key);
    }
}
