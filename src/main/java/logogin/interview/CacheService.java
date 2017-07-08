package logogin.example3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * CacheService.java
 * TODO: replace with persistent distributed cache
 *
 * @created Dec 5, 2012
 * @author logogin
 */
@Service
public class CacheService {

    private static final Logger log = LoggerFactory.getLogger(CacheService.class);

    /**
     * stores code.id.field=value pairs; used to detect field updates.
     */
    private final Cache<String, Object> fieldCache = CacheBuilder.newBuilder().maximumSize(10000).build();


    /**
     * stores code.id=writeTime pairs; update expiration detection.
     */
    private final Cache<String, Long> updateCache = CacheBuilder.newBuilder().maximumSize(1000).build();

    public boolean isExpired(String code, String id, int exp) {
        Long writeTime = updateCache.getIfPresent(createKey(code, id));
        if ( null == writeTime ) {
            return true;
        }
        long ttl = TimeUnit.SECONDS.toNanos(exp) - (System.nanoTime() - writeTime);
        log.debug("isExpired: ttl={} sec", TimeUnit.NANOSECONDS.toSeconds(ttl));
        return ttl <= 0;
    }

    /**
     * Detects differences between cache and record; updates record with cached values.
     * @param code
     * @param id
     * @param record
     * @param requiredFields - set of mandatory fields to be inspected in record.
     * @param optionalFields - set of optional fields to be inspected in record.
     * @return <code>true</code> if at least one field different from cached; <code>false</code> otherwise.
     */
    public boolean mergeFromCache(String code, String id, Map<String, Object> record, Set<String> requiredFields, Set<String> optionalFields) {
        boolean mandatory = mergeFields(code, id, record, requiredFields, false);
        boolean optional = mergeFields(code, id, record, optionalFields, true);
        return mandatory || optional;
    }

    public void putFields(String code, String id, Map<String, Object> record, Set<String> fields) {
        for ( String field : fields ) {
            if ( record.containsKey(field) ) {
                fieldCache.put(createKey(code, id, field), record.get(field));
            }
        }
    }

    public void setUpdated(String code, String id) {
        updateCache.put(createKey(code, id), System.nanoTime());
        log.debug("put: cache size fieldCache={} updateCache={}", new Object[] {fieldCache.size(), updateCache.size()});
    }

    private boolean mergeFields(String code, String id, Map<String, Object> record, Set<String> fields, boolean optional) {
        boolean changed = false;
        for ( String field : fields ) {
            Optional<Object> cached = Optional.fromNullable(fieldCache.getIfPresent(createKey(code, id, field)));
            Optional<Object> current = Optional.fromNullable(record.get(field));

            //Can't be null at this point; cache must contain value or this is the first record.
            current = current.or(cached);
            if ( !current.isPresent() ) {
                if ( !optional ) {
                    throw new IllegalArgumentException(String.format("field=%s required field is missing in both record and cache", field));
                }
                log.warn("mergeFields: field={} optional field is missing in both record and cache", field);
                continue;
            }
            record.put(field, current.get());
            /**
             * current  cache  equals  changed
             *   null    null   ---     ---    illegal
             *    x      null   false   true   first record
             *  null->x   x     true    false  no update
             *    x       y     false   true   updated
             */
            if ( !changed ) {
                changed = !current.get().equals(cached.orNull());
            }
        }
        return changed;
    }

    private String createKey(String code, String id, String field) {
        return String.format("%s.%s.%s", code, id, field);
    }

    private String createKey(String id, String field) {
        return String.format("%s.%s", id, field);
    }

    /**
     * debugging
     */
    public Map<String, Object> dump(String code, String id) {
        Set<String> keys = fieldCache.asMap().keySet();
        String prefix = String.format("%s.%s.", code, id);
        Map<String, Object> cached = new HashMap<String, Object>();
        for ( String key : keys ) {
            if ( key.startsWith(prefix) ) {
                String field = key.substring(prefix.length());
                cached.put(field, fieldCache.getIfPresent(key));
            }
        }
        return cached;
    }

    /**
     * test seam
     */
    protected Cache<String, Object> getFieldCache() {
        return fieldCache;
    }

    /**
     * test seam
     */
    protected Cache<String, Long> getUpdateCache() {
        return updateCache;
    }
}
