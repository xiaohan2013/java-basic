package org.example;

import java.util.Arrays;

public class PlusOneSolver {
    static int[] Solve(int[] digits) {
        if(digits.length == 0 || digits == null) {
            return new int[0];
        }

        int[] result = new int[digits.length - 1];

        int sumPlusOne = Arrays.stream(digits).reduce(0, (a, b) -> a * 10 + b) + 1;

        int u = sumPlusOne;
        int i = 0;
        while (i < result.length) {
            int e = sumPlusOne / (int)Math.pow(10, i);
            result[i] = e;
        }

        return result;
    }
    public static void main(String[] args) {
        int[] r = Solve(new int[]{1, 2, 3});
        Arrays.stream(r).forEach(System.out::print);
    }
}
