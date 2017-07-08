package logogin.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CopyOfDuplicates {

    public static List<Integer> readArray(Scanner scanner, int n) {
        List<Integer> input = new ArrayList<Integer>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            for (int j = 0; j < nums.length; j++) {
                input.add(Integer.valueOf(nums[j]));
            }
        }
        return input;
    }

    public static boolean checkNum(Map<Integer, Set<Integer>> lookup, int num, int index, int dist) {
        Set<Integer> indexes = lookup.get(num);
        if ( null == indexes ) {
            indexes = new HashSet<Integer>();
            indexes.add(index);
            lookup.put(num, indexes);
        }

        for ( int ind : indexes ) {
            if ( ind != index && Math.abs(ind-index) <= dist) {
                return true;
            }
        }
        indexes.add(index);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> array = readArray(scanner, n);
        int dist = scanner.nextInt();

        Map<Integer, Set<Integer>> lookup = new HashMap<Integer, Set<Integer>>();

        for ( int i=0; i<array.size(); i++ ) {
            if ( checkNum(lookup, array.get(i), i, dist) ) {
                System.out.println("YES");
                System.exit(0);
            }
        }
        System.out.println("NO");
    }
}
