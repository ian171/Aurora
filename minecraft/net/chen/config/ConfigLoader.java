package net.chen.config;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ConfigLoader {
    Yaml yaml = new Yaml();
    private static final String jarPath = System.getProperty("user.dir");
    Path path = Paths.get(jarPath+"\\config");


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
            Config.getInstance().setConfigValue(entry.getKey(), entry.getValue());
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
        //writeConfig(file);
        copyFileFromJar("config/config.yml",jarPath+"/config/config.yml");
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
    public static void copyFileFromJar(String jarFilePath, String systemPath) {
        // 获取类加载器
        ClassLoader classLoader = ConfigLoader.class.getClassLoader();

        // 从 JAR 文件中获取文件输入流
        InputStream inputStream = classLoader.getResourceAsStream(jarFilePath);

        // 如果输入流为空，说明文件不存在
        if (inputStream == null) {
            throw new RuntimeException("File not found in JAR: " + jarFilePath);
        }

        // 创建目标路径的 Path 对象
        Path targetPath = Path.of(systemPath);

        // 复制文件
        try {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy file", e);
        }
    }

}
