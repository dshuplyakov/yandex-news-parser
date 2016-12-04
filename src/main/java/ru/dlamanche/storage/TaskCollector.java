package ru.dlamanche.storage;

import com.google.inject.Inject;
import ru.dlamanche.job.JobParseNews;

import java.util.concurrent.ExecutorService;

/**
 * Date: 04.12.2016
 * Time: 22:26
 *
 * @author Dmitry Shuplyakov
 */
public class TaskCollector {

    final String DEFAULT_EXECUTOR = "News";

    @Inject
    HazelcastProvider hazelcastProvider;

    public void addNewsUrl(String url) {
        ExecutorService executor = hazelcastProvider.getInstance().getExecutorService(DEFAULT_EXECUTOR);
        executor.submit(new JobParseNews(url));
    }
}
