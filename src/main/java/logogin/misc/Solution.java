package logogin.misc;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static int[][] readInput() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int array[][] = new int[n][n];
        for ( int i=0; i<n; i++ ) {
            for ( int j = 0; j<n; j++ ) {
                int num = scanner.nextInt();
                array[i][j] = num;
            }
        }
        return array;
    }

    public static String join(int[] row, String sep) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            if (i > 0) {
                buf.append(sep);
            }
            buf.append(row[i]);
        }
        return buf.toString();
    }

    public static void printOutput(int[][] array) {
        for ( int i = 0; i < array.length; i++ ) {
            System.out.println(join(array[i], " "));
        }
    }

    public static int[] shiftArray(int[] input) {
        int result[] = new int[input.length];
        for (int i=1; i<input.length; i++) {
            result[i] = input[i-1];
        }
        result[0] = input[input.length-1];
        return result;
    }

    public static int[] readCircle(int[][] array, int offset) {
        int n = array.length;
        List<Integer> result = new LinkedList<Integer>();
        int left = offset;
        int top = offset;
        int right = n - offset - 1;
        int lower = n - offset - 1;

        for ( int col=left; col<=right; col++ ) {
            result.add(array[top][col]);
        }
        for ( int row=top+1; row<=lower; row++) {
            result.add(array[row][right]);
        }
        for ( int col=right-1; col>=left; col--) {
            result.add(array[lower][col]);
        }
        for ( int row=lower-1; row>=top+1; row--) {
            result.add(array[row][left]);
        }
        int[] intResult = new int[result.size()];
        for ( int i=0; i<result.size(); i++ ) {
            intResult[i] = result.get(i);
        }
        return intResult;
    }

    public static void writeCircle(int circle[], int[][] array, int offset) {
        int n = array.length;
        int left = offset;
        int top = offset;
        int right = n - offset - 1;
        int lower = n - offset - 1;

        int k = 0;
        for ( int col=left; col<=right; col++ ) {
            array[top][col] = circle[k++];
        }
        for ( int row=top+1; row<=lower; row++) {
            array[row][right] = circle[k++];
        }
        for ( int col=right-1; col>=left; col--) {
            array[lower][col] = circle[k++];
        }
        for ( int row=lower-1; row>=top+1; row--) {
            array[row][left] = circle[k++];
        }
    }

    public static void main(String args[]) throws Exception {
        int[][] array = readInput();
        int n = array.length;

        for ( int i=0; i<n/2; i++ ) {
            int[] circle = readCircle(array, i);
            int[] shifted = shiftArray(circle);
            writeCircle(shifted, array, i);
        }

        printOutput(array);
    }

}
