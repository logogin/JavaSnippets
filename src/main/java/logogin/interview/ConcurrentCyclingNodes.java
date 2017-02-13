package logogin.example2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentCyclingNodes.java
 *
 * @created Nov 29, 2012
 * @author Pavel Danchenko
 */
public class ConcurrentCyclingNodes<T> {

    private final T[] nodes;
    private final AtomicInteger index = new AtomicInteger(0);

    public ConcurrentCyclingNodes(T ... nodes) {
        this.nodes = nodes;
    }

    public T next() {
        int current = Math.abs(index.getAndIncrement()) % nodes.length;
        return nodes[current];
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
                return returned < ConcurrentCyclingNodes.this.size();
            }

            public T next() {
                if(hasNext()) {
                    T item = ConcurrentCyclingNodes.this.next();
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
        return nodes.length;
    }

    public T[] all() {
        return Arrays.copyOf(nodes, nodes.length);
    }
}
