package logogin.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Duplicates {

    public static int[][] readArray(Scanner scanner, int n) {
        List<Integer> input = new LinkedList<Integer>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            for (int j = 0; j < nums.length; j++) {
                input.add(Integer.valueOf(nums[j]));
            }
        }
        int[][] array = createArray(n, input);
        return array;
    }

    public static int[][] createArray(int n, List<Integer> input) {
        int cols = input.size() / n;
        int[][] result = new int[n][cols];
        int k = 0;
        for ( int i=0; i<n; i++ ) {
            for (int j=0; j<cols; j++) {
                result[i][j] = input.get(k++);
            }
        }
        return result;
    }

    public static class Pair {
        public int row;
        public int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (col != other.col)
                return false;
            if (row != other.row)
                return false;
            return true;
        }
    }

    public static boolean checkNum(Map<Integer, Set<Pair>> lookup, int num, int row, int col, int dist) {
        Pair thisPair = new Pair(row, col);
        Set<Pair> pairs = lookup.get(num);
        if ( null == pairs ) {
            pairs = new HashSet<Pair>();
            pairs.add(thisPair);
            lookup.put(num, pairs);
        }

        for ( Pair pair : pairs ) {
            if ( !thisPair.equals(pair) && withinDistance(pair, thisPair, dist)) {
                return true;
            }
        }
        pairs.add(thisPair);
        return false;
    }

    public static boolean withinDistance(Pair p1, Pair p2, int dist) {
        return Math.abs(p1.row - p2.row) <= dist || Math.abs(p1.col - p2.col) <= dist;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] array = readArray(scanner, n);
        int dist = scanner.nextInt();

        Map<Integer, Set<Pair>> lookup = new HashMap<Integer, Set<Pair>>();

        int rows = n;
        int cols = array[0].length;
        for ( int i=0; i<rows; i++ ) {
            for (int j=0; j<cols; j++) {
                if ( checkNum(lookup, array[i][j], i, j, dist) ) {
                    System.out.println("YES");
                    System.exit(0);
                }
            }
        }
        System.out.println("NO");
    }
}
