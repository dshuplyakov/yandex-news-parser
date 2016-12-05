package ru.dlamanche.statistic;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Inject
    HazelcastProvider hazelcastProvider;

    public void showStats() {
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(getStat());
        }, 0, 5, TimeUnit.SECONDS);

        shutdown();
    }

    private void shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    scheduler.awaitTermination(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    scheduler.shutdown();
                }
            }
        });
    }

    private String getStat() {
        return String.format("Rss proceed: %d", hazelcastProvider.getMap().size());
    }
}
