package logogin.interview.failover;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;

/**
 * LoadBalancedRefreshMetadataService.java
 *
 * @created Nov 5, 2012
 * @author logogin
 */
public class LoadBalancedRefreshMetadataService {

    private static final Logger log = LoggerFactory.getLogger(LoadBalancedRefreshMetadataService.class);

    private ConcurrentCyclingNodes<String> nodes;
    private RestTemplate restTemplate;

    private @Value("${servers}") String[] servers;
    private @Value("${restUriTemplate}") String restUriTemplate;
    private @Value("${failOver.attempts:0}") int failOverAttempts = 0;
    private @Value("${failOver.logExceptions}") boolean failOverLogExceptions;

    private final FailOverLoadBalancer<ResponseEntity<String>> balancer = new FailOverLoadBalancer<ResponseEntity<String>>() {
        @Override
        public boolean continueFailOver(ResponseEntity<String> result) {
            if ( null != result && result.getStatusCode() == HttpStatus.OK ) {
                return false;
            }
            return true;
        }
    };

    @PostConstruct
    public void init() {
        String[] endpoints = prepareEndpoints();
        nodes = new ConcurrentCyclingNodes<String>(endpoints);
        if ( failOverAttempts < 1 ) {
            failOverAttempts = nodes.size();
        }
    }

    public void refreshMetadata(final int id, final DateTime timestamp) {
        log.debug("refreshMetadata: executing fail-over: id={} timestamp={}", id, formatId, timestamp);
        balancer.failOver(nodes, failOverAttempts, new FailOverLoadBalancer.Operation<String, ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> execute(String endpoint) {
                ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class
                        , ImmutableMap.of("id", id, "timestamp", timestamp.getMillis()));
                return response;
            }

            @Override
            public void handleFailure(String node, Throwable exception) {
                if (failOverLogExceptions) {
                    log.warn("refreshMetadata: failed id={} timestamp={} node={} reason={}", id, timestamp, node, exception.getMessage());
                }
            }
        });
    }

    private String[] prepareEndpoints() {
        String[] result = new String[servers.length];
        for ( int i = 0; i< servers.length; i++ ) {
            result[i] = String.format("http://%s%s", servers[i], uriTemplate);
        }
        return result;
    }
}
