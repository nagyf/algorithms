package hu.nagyf.algorithms.datastructures;

import java.util.Iterator;

/**
 * Base class for iterators with some useful methods.
 *
 * @param <T> the type of values to iterate upon
 */
public abstract class AbstractIterator<T> implements Iterator<T> {

    @Override
    public abstract boolean hasNext();

    @Override
    public abstract T next();

    /**
     * Converts the iterator into a linked list by putting every value into a list that the iterator visits.
     */
    public LinkedList<T> toList() {
        var list = new LinkedList<T>();

        while(hasNext()) {
            list.append(next());
        }

        return list;
    }
}
