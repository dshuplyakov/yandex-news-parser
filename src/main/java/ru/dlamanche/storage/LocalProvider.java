package ru.dlamanche.storage;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 02.12.2016
 * Time: 17:24
 *
 * @author Dmitry Shuplyakov
 */
public class LocalProvider<E> implements StorageProvider {

    private HashMap<String, List<E>> storage;

    @Override
    public List<E> getList(String name) {
        return storage.get(name);
    }

    @Override
    public void setList(List list, String name) {
        storage.put(name, list);
    }
}
