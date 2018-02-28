package com.salesforce;

public class OuterClass {

    private int x = 1;
    private static int r;

    public class InnerClass {
        private int y;

        public InnerClass(int y) {
            this.y = y;
        }

        public int foo(int z) {
            System.out.println("r: " + r);

            return x + y + z;
        }
    }

    public static class StaticInnerClass {
        private int m = 0;

        public StaticInnerClass(int m) {
            this.m = m;
        }

        public int bar(int n) {
            return r + m + n;
        }
    }
}
