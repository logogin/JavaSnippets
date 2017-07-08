package logogin.interview.failover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FailOverLoadBalancer.java
 *
 * @created Nov 5, 2012
 * @author logogin
 */
public class FailOverLoadBalancer<R> {

    private static final Logger log = LoggerFactory.getLogger(FailOverLoadBalancer.class);

    private boolean logFailedOperations = false;

    public interface Operation<T, R> {
        /**
         * @param node
         * @return R for success and stop of failover, null or exception to continue
         */
        public R execute(T node);

        public void handleFailure(T node, Throwable exception);
    }

    /**
     * @param nodes
     * @param maxAttempts
     * @param operation
     * @return true if operation succeeded, false if all attempts failed
     */
    public <T, R> R failOver(ConcurrentCyclingNodes<T> nodes, int maxAttempts, Operation<T, R> operation) {
        for ( int i = 0; i < maxAttempts; i++ ) {
            T node = nodes.next();
            try {
                R result = operation.execute(node);
                if ( !continueFailOver(result) ) {
                    //got the result, should stop cycling, success
                    return result;
                }
            } catch (Throwable ex) {
                if ( logFailedOperations ) {
                    log.warn("Exception caught while executing operation={} on node={}", operation, node, ex);
                }
                operation.handleFailure(node, ex);
            }
        }
        return null;
    }

    public boolean continueFailOver(R result) {
        if ( null == result ) {
            return true;
        }
        return false;
    }

    public void setLogFailedOperations(boolean logFailedOperations) {
        this.logFailedOperations = logFailedOperations;
    }
}
