package ru.dlamanche.storage;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.List;

/**
 * Date: 02.12.2016
 * Time: 19:05
 *
 * @author Dmitry Shuplyakov
 */
public class HazelcastProvider implements StorageProvider {

    HazelcastInstance instance;

    public HazelcastProvider() {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
    }

    @Override
    public <E> List<E> getList(String name) {
        return instance.getList(name);
    }

    @Override
    public <E> void setList(List<E> list, String name) {
        instance.getList(name).addAll(list);
    }
}
