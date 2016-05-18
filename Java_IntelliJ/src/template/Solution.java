package template;

import java.io.InputStream;
import java.util.Scanner;

public class Solution {
    // Solution function gets inputs from InputStream object and prints the result to standard output.
    // You can use InputStream object in the same way as System.in.

    // Example solution function 1
    public static void solution1(InputStream istream) {
        Scanner sc = new Scanner(istream);
        int testNum = Integer.parseInt(sc.nextLine());

        for (int testCnt = 0; testCnt < testNum; testCnt++) {
            int nTimes = Integer.parseInt(sc.nextLine());

            for (int cnt = 0; cnt < nTimes; cnt++) {
                System.out.println("Hello world!");
            }
        }
    }

    // Example solution function 2
    public static void solution2(InputStream istream) {
        Scanner sc = new Scanner(istream);
        int testNum = Integer.parseInt(sc.nextLine());

        int totalNum = 0;
        for (int testCnt = 0; testCnt < testNum; testCnt++) {
            totalNum += Integer.parseInt(sc.nextLine());
        }

        for (int cnt = 0; cnt < totalNum; cnt++) {
            System.out.println("Hello world!");
        }
    }

    public static void main(String[] args) {
        // Can be tested on console.
        solution1(System.in);
        // solution2(System.in);
    }
}
