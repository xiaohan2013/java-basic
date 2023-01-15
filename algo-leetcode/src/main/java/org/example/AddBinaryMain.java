package org.example;

public class AddBinaryMain {
    static String Solver(String a, String b) {
        if(a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }

        int a_i = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            a_i += Math.pow(2, i) * Character.digit(a.charAt(a.length() - i - 1), 10);
        }


        int b_i = 0;
        for (int i = b.length() - 1; i >= 0; i--) {
            b_i += Math.pow(2, i) * Character.digit(b.charAt(b.length() - i - 1), 10);
        }

        int r = a_i + b_i;

        return Integer.toString(r, 2);
    }
    public static void main(String[] args) {
        String r = Solver("1010", "1011");
        System.out.println(r);
    }
}
