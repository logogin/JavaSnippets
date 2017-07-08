package logogin.interview.failover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RoundRobinExecutor.java
 *
 * @created Nov 5, 2012
 * @author logogin
 */
public class RoundRobinExecutor {

    private static final Logger log = LoggerFactory.getLogger(RoundRobinExecutor.class);

    public interface Operation<T, R> {
        public boolean execute(T item);
    }

    public <R, T> void cycle(RoundRobin<T> items, int count, Operation<T, R> op) {
        for ( int i = 0; i < count; i++ ) {
            T item = items.next();
            try {
                if ( op.execute(item) ) {
                    //got the result, should stop cycling
                    break;
                }
            } catch (Exception ex) {
                log.warn("Exception caught while executing operation on item={}", item, ex);
            }
        }
    }
}
