package ru.dlamanche.provider;

import com.google.inject.AbstractModule;
import ru.dlamanche.DataCollector;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.http.HttpClient;

/**
 * Date: 02.12.2016
 * Time: 12:46
 *
 * @author Dmitry Shuplyakov
 */
public class ConfigModule extends AbstractModule {

    private MainConfig mainConfig;

    public ConfigModule(MainConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    @Override
    protected void configure() {
        bind(MainConfig.class).toInstance(mainConfig);
        bind(DataCollector.class).asEagerSingleton();
        bind(HttpClient.class).toInstance(new HttpClient("http-crawler", mainConfig.httpClientConfig));
    }
}
