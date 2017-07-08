package logogin.interview.failover;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RoundRobin.java
 *
 * @created Nov 5, 2012
 * @author logogin
 */
public class RoundRobin<T> {

    private final T[] items;
    private final AtomicInteger index = new AtomicInteger(0);

    public RoundRobin(String itemsList) {
        this.items = (T[])itemsList.split(" ");
    }

    public RoundRobin(T ... items) {
        this.items = items;
    }

    public T next() {
        int current = Math.abs(index.getAndIncrement()) % items.length;
        return items[current];
    }

    public Iterable<T> items() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator();
            }
        };
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int returned = 0;

            public boolean hasNext() {
                return returned < RoundRobin.this.size();
            }

            public T next() {
                if(hasNext()) {
                    T item = RoundRobin.this.next();
                    returned++;
                    return item;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public int size() {
        return items.length;
    }

    public T[] all() {
        return Arrays.copyOf(items, items.length);
    }
}
