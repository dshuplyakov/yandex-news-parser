package ru.dlamanche;

import com.google.inject.Inject;
import ru.dlamanche.storage.HazelcastProvider;

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
    HazelcastProvider hazelcastProvider;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void showStats() {
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(getStat());
        }, 0, 5, TimeUnit.SECONDS);
    }

    private String getStat() {
        return String.format("Rss proceed: %d", hazelcastProvider.getMap().size());
    }
}
