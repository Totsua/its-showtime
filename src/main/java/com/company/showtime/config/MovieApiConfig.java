package com.company.showtime.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MovieApiConfig {
    private static final Properties properties = new Properties();
    private static final String configFilePath = "src/main/resources/api.properties";

    static{
        try(FileInputStream configFileReader = new FileInputStream(new File(configFilePath))){
            properties.load(configFileReader);
        }catch (IOException e){
            System.out.println(e.getMessage());
            // todo: throw a custom error with code 500
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getAuthHeader(){
        return properties.getProperty("api.authorization");
    }
    public static String getClientHeader(){
        return properties.getProperty("api.client");
    }
    public static String getApiKey(){
        return properties.getProperty("api.xkey");
    }

    public static String getTerritory(){
        return properties.getProperty("api.territory");
    }

}