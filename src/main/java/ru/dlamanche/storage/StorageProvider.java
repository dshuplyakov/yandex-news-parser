package ru.dlamanche.storage;

import java.util.List;

/**
 * Date: 02.12.2016
 * Time: 21:48
 *
 * @author Dmitry Shuplyakov
 */
public interface StorageProvider {

    <E> List<E> getList(String name);

    <E> void setList(List<E> list, String name);

}
