package logogin.interview.failover;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;

/**
 * RefreshMetadataService.java
 *
 * @created Nov 5, 2012
 * @author logogin
 */
@Service
public class RefreshMetadataService {

    private @Autowired RoundRobin<String> servers;
    private @Autowired RestTemplate restTemplate;

    private @Value("${restUriTemplate}") String uriTemplate;

    public void refreshMetadata(final int id, final long timestamp) {
        RoundRobinExecutor executor = new RoundRobinExecutor();
        executor.cycle(servers, servers.size(), new RoundRobinExecutor.Operation<String, Boolean>() {
            @Override
            public boolean execute(String server) {
                try {
                    restTemplate.getForObject(server + uriTemplate, Object.class
                            , ImmutableMap.of("id", id, "timestamp", timestamp));
                } catch (HttpServerErrorException ex) {
                    return false;
                }
                return true;
            }
        });
    }
}
