import java.util.HashMap;
import java.util.Map;

/**
 * MostCommonSum.java
 * Task: Given an array of n integers, find the sum of the most common integer.
 * Example: (2,4,5,6,4) – return 8 (as 4+4 = 8).
 * (1,2,1,3,1) – return 3 (as 1+1+1 = 3).
 *
 * @created May 27, 2012
 * @author Pavel Danchenko
 */
public class MostCommonInteger {

    /**
     * Optimized for speed. Uses java.util.HashMap for lookup integers. Complexity is about O(n)
     * @param data - input array
     * @return sum of the most coomon integer
     */
    public int sumFast(int[] data) {
        if ( data == null || data.length == 0 ) {
            throw new IllegalArgumentException("Invalid input");
        }
        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        int max = 1;
        int common = data[0];
        for ( int key : data) {
            int count = 1;
            if ( lookup.containsKey(key) ) {
                count = lookup.get(key) + 1;
            }
            lookup.put(key, count);
            if ( max < count ) {
                max = count;
                common = key;
            }
        }
        return max * common;
    }

    /**
     * Optimized for space. Uses square loop to lookup for common integers. Complexity is about O(n*n)
     * @param data - input array
     * @return sum of the most coomon integer
     */
    public int sumSpace(int[] data) {
        if ( data == null || data.length == 0 ) {
            throw new IllegalArgumentException("Invalid input");
        }
        int max = 1;
        int common = data[0];
        for ( int i = 0; i<data.length - 1; i++) {
            int count = 1;
            for ( int j = i+1; j<data.length; j++ ) {
                if (data[i] == data[j]) {
                    count++;
                }
            }
            if ( max < count ) {
                max = count;
                common = data[i];
            }
        }
        return max * common;
    }
}
