package hu.nagyf.algorithms.datastructures;

import java.util.Iterator;

public abstract class AbstractIterator<T> implements Iterator<T> {

    @Override
    public abstract boolean hasNext();

    @Override
    public abstract T next();

    public LinkedList<T> toList() {
        var list = new LinkedList<T>();

        while(hasNext()) {
            list.append(next());
        }

        return list;
    }
}
