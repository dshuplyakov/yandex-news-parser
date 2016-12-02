package ru.dlamanche;

import com.google.inject.Inject;
import ru.dlamanche.config.MainConfig;
import ru.dlamanche.http.HttpClient;

import java.io.IOException;

/**
 * Date: 02.12.2016
 * Time: 12:47
 *
 * @author Dmitry Shuplyakov
 */
public class NewsImporter {

    @Inject
    HttpClient httpClient;

    @Inject
    MainConfig mainConfig;

    public void start() {

        try {
            String a = httpClient.sendGet(mainConfig.yandexUrl);
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("started!");

    }
}
