package ru.dlamanche.storage;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.List;
import java.util.Map;

/**
 * Date: 02.12.2016
 * Time: 19:05
 *
 * @author Dmitry Shuplyakov
 */
public class HazelcastProvider {

    private final static String DEFAULT_MAP = "News";

    HazelcastInstance instance;

    public HazelcastProvider() {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
    }

    public HazelcastInstance getInstance() {
        return instance;
    }

    public <E> Map<String, List<E>> getMap() {
        return instance.getMap(DEFAULT_MAP);
    }

    public <E> void addToMap(List<E> list, String url) {
        instance.getMap(DEFAULT_MAP).put(url, list);
    }
}
