package logogin.misc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution1 {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<Integer>(n);
        for ( int i=0; i<n; i++ ) {
            int num = scanner.nextInt();
            numbers.add(num);
        }
        int prev = numbers.get(0);
        int next = numbers.get(1);
        int factor = next - prev;
        for (int i=1; i<n; i++ ) {
            prev = numbers.get(i-1);
            next = numbers.get(i);
            int newFactor = next - prev;
            if (newFactor != factor) {
                System.out.println(prev + factor);
                break;
            }
        }
    }
}
