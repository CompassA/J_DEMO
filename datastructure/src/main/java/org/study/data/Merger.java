package org.study.data;

/**
 * @author fanqie
 * @date 2020/5/18
 */
public interface Merger<E> {

    E merge(final E a, final E b);
}
