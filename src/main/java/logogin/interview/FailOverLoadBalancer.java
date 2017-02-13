package logogin.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FailOverLoadBalancer.java
 *
 * @created Nov 29, 2012
 * @author Pavel Danchenko
 */
public class FailOverLoadBalancer {

    private static final Logger log = LoggerFactory.getLogger(FailOverLoadBalancer.class);

    /**
     * Unit of work on single node
     */
    public interface Operation<T, R> {
        /**
         * @param node - for this operation
         * @return R for success and stop of failover, null or exception to continue
         */
        public R execute(T node);
    }

    /**
     * @param nodes - cycling nodes
     * @param maxAttempts - including successful, i.e. maximum number executions of operation.
     * @param operation - over single node
     * @return true if operation succeeded, false if all attempts failed
     */
    public <T, R> R failOver(ConcurrentCyclingNodes<T> nodes, int maxAttempts, Operation<T, R> operation) {
        for ( int i = 0; i < maxAttempts; i++ ) {
            T node = nodes.next();
            try {
                R result = operation.execute(node);
                if ( null != result ) {
                    //got the result, should stop cycling, success
                    return result;
                }
            } catch (Throwable ex) {
                log.warn("Exception caught while executing operation={} on node={}", operation, node, ex);
            }
        }
        return null;
    }
}
