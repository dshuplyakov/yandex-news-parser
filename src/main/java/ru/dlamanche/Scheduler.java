package ru.dlamanche;

import com.google.inject.Inject;
import ru.dlamanche.storage.StorageProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 02.12.2016
 * Time: 22:09
 *
 * @author Dmitry Shuplyakov
 */
public class Scheduler {

    @Inject
    StorageProvider storageProvider;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void start() {

        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(storageProvider.getList("news").size());
        }, 0, 5, TimeUnit.SECONDS);

    }
}
