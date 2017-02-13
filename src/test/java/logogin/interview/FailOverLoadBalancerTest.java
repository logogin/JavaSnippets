package logogin.example2;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.mockito.InOrder;
import org.testng.annotations.Test;

/**
 * FailOverLoadBalancerTest.java
 *
 * @created Nov 29, 2012
 * @author Pavel Danchenko
 */
@Test
public class FailOverLoadBalancerTest {

    public void failOver_success_once() {
        FailOverLoadBalancer balancer = new FailOverLoadBalancer();

        FailOverLoadBalancer.Operation<String, Boolean> operation = mock(FailOverLoadBalancer.Operation.class);
        when(operation.execute(anyString())).thenReturn(true);

        boolean result = balancer.failOver(new ConcurrentCyclingNodes<String>("test_A", "test_B", "test_C"), 3, operation);

        assertTrue(result);
        verify(operation).execute("test_A");
    }

    public void failOver_fail_success() {
        FailOverLoadBalancer balancer = new FailOverLoadBalancer();

        FailOverLoadBalancer.Operation<String, Boolean> operation = mock(FailOverLoadBalancer.Operation.class);
        when(operation.execute(anyString())).thenReturn(null, true);

        boolean result = balancer.failOver(new ConcurrentCyclingNodes<String>("test_A", "test_B", "test_C"), 3, operation);

        assertTrue(result);
        InOrder order = inOrder(operation);
        order.verify(operation).execute("test_A");
        order.verify(operation).execute("test_B");
    }

    public void failOver_fail() {
        FailOverLoadBalancer balancer = new FailOverLoadBalancer();

        FailOverLoadBalancer.Operation<String, Boolean> operation = mock(FailOverLoadBalancer.Operation.class);
        when(operation.execute(anyString())).thenReturn(null);

        Object result = balancer.failOver(new ConcurrentCyclingNodes<String>("test_A", "test_B", "test_C"), 3, operation);

        assertNull(result);
        InOrder order = inOrder(operation);
        order.verify(operation).execute("test_A");
        order.verify(operation).execute("test_B");
        order.verify(operation).execute("test_C");
    }

    public void failOver_cycle_fail() {
        FailOverLoadBalancer balancer = new FailOverLoadBalancer();

        FailOverLoadBalancer.Operation<String, Boolean> operation = mock(FailOverLoadBalancer.Operation.class);
        when(operation.execute(anyString())).thenReturn(null);

        Object result = balancer.failOver(new ConcurrentCyclingNodes<String>("test_A", "test_B", "test_C"), 4, operation);

        assertNull(result);
        InOrder order = inOrder(operation);
        order.verify(operation).execute("test_A");
        order.verify(operation).execute("test_B");
        order.verify(operation).execute("test_C");
        order.verify(operation).execute("test_A");
    }

    public void failOver_exception_success() {
        FailOverLoadBalancer balancer = new FailOverLoadBalancer();

        FailOverLoadBalancer.Operation<String, Boolean> operation = mock(FailOverLoadBalancer.Operation.class);
        when(operation.execute(anyString())).thenThrow(new RuntimeException("test")).thenReturn(true);

        boolean result = balancer.failOver(new ConcurrentCyclingNodes<String>("test_A", "test_B", "test_C"), 3, operation);

        assertTrue(result);
        InOrder order = inOrder(operation);
        order.verify(operation).execute("test_A");
        order.verify(operation).execute("test_B");
    }
}
