package logogin.interview.failover;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentCyclingNodes.java
 *
 * @created Nov 5, 2012
 * @author logogin
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


    /**
     * @param infinite if true - cycle infinitely, false - single loop over all items
     * @return Iterable<T>
     */
    public Iterable<T> items(boolean infinite) {
        if ( infinite ) {
            return new Iterable<T>() {
                @Override
                public Iterator<T> iterator() {
                    return infiniteIterator();
                }
            };
        }
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

            @Override
            public boolean hasNext() {
                return returned < ConcurrentCyclingNodes.this.size();
            }

            @Override
            public T next() {
                if(hasNext()) {
                    T item = ConcurrentCyclingNodes.this.next();
                    returned++;
                    return item;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<T> infiniteIterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return ConcurrentCyclingNodes.this.next();
            }

            @Override
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
