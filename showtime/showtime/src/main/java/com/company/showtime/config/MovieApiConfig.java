package com.company.showtime.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MovieApiConfig {
    private Properties properties;
    private final String configFilePath = "src/main/resources/api.properties";

    public MovieApiConfig(){
        File configFile = new File(configFilePath);
        properties = new Properties();
        try{
            FileInputStream configFileReader = new FileInputStream(configFile);
            try{
            properties.load(configFileReader);
            configFileReader.close();
            }catch (FileNotFoundException e){
                System.out.println(e.getMessage());
                // todo: throw a custom error with code 500...
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getAuthHeader(){
        return properties.getProperty("api.auth");
    }
    public String getClientHeader(){
        return properties.getProperty("api.client");
    }
    public String getApiKey(){
        return properties.getProperty("api.key");
    }


}
