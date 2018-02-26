package com.xyzcorp;

public class OuterClass {
    private final int x = 1;
    private final static int r = 5;

    public class InnerClass {
        private int y;

        public InnerClass(final int y) {
            this.y = y;
        }

        public int foo(final int z) {
            System.out.println(r);
            return x + y + z;
        }
    }

    public static class StaticInnerClass {
        private final int m;

        public StaticInnerClass(final int m) {
            this.m = m;
        }

        public int bar(final int n) {
            return r + m + n;
        }
    }
}
