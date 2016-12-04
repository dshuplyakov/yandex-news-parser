package ru.dlamanche.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.http.client.HttpClientConfig;

import java.io.File;
import java.io.IOException;

/**
 * Date: 02.12.2016
 * Time: 12:47
 *
 * @author Dmitry Shuplyakov
 */
public class MainConfig {

    @JsonProperty("yandexUrl")
    public String yandexUrl;

    @JsonProperty("httpClient")
    public HttpClientConfig httpClientConfig;

    private static final Logger log = LoggerFactory.getLogger(MainConfig.class);

    public static MainConfig load(String configFile) {
        return MainConfig.readConfigFile(configFile);
    }

    private static MainConfig readConfigFile(String configFile) {
        log.info("Loading configuration from " + configFile);
        File f = new File(configFile);
        if (!f.exists() || f.isDirectory()) {
            throw new IllegalArgumentException("Config file not exists");
        }

        MainConfig mainConfig;
        try {
            mainConfig = new ObjectMapper(new YAMLFactory()).readValue(f, MainConfig.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Config parsing IOException error: ", e);
        }

        if (mainConfig.httpClientConfig == null) {
            mainConfig.httpClientConfig = new HttpClientConfig(10, 3000, 10000, 3, false);
        }

        return mainConfig;
    }

}
