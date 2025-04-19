package net.chen.config;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigLoader {
    Yaml yaml = new Yaml();
    private static final String jarPath = System.getProperty("user.dir");
    Path path = Paths.get(jarPath+"\\config");
    private Config config = new Config();

    public ConfigLoader() {
        System.out.println("Jar path"+jarPath);
        System.out.println(path);
    }
    public void loadConfig() {
        System.out.println("Path is "+path+"\nJar path is "+jarPath);
        System.out.println("Loading config file from path: " + this.path + "\\config.yml");
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(jarPath + "\\config\\config.yml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Assuming Config has a field 'config' of type Map<String, Object>
        Map<String, Object> configData = yaml.load(inputStream);
        for (Map.Entry<String, Object> entry : configData.entrySet()) {
            config.setConfigValue(entry.getKey(), entry.getValue());
        }

    }

    public void makeDir(){
        System.out.println(path);
        try {
            Path pathCreate = Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void makeConfig(){
        if (new File(jarPath+"/config/config.yml").exists()){
            return;
        }
        File file = new File(jarPath+"/config/config.yml");
        writeConfig(file);
    }
    public void writeConfig(File file){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("""
                    test: 123
                    """);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
