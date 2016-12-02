package ru.dlamanche;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.provider.ConfigModule;

public class App
{

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        try {
            start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void start() {

        final MainConfig config = MainConfig.load("config.yml");

        Injector injector = Guice.createInjector(
                new ConfigModule(config)
        );

        injector.getInstance(NewsImporter.class).start();
    }
}
