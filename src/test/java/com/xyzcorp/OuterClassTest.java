package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class OuterClassTest {

    @Test
    public void testInnerClass() {
        OuterClass.InnerClass oic = new OuterClass().new InnerClass(2);
        assertThat(oic.foo(5)).isEqualTo(8);
    }

    @Test
    public void testTwoInnerClasses() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass oic = outerClass.new InnerClass(10);
        OuterClass.InnerClass oic2 = outerClass.new InnerClass(12);
        assertThat(oic.foo(5)).isEqualTo(16);
        assertThat(oic2.foo(5)).isEqualTo(18);
    }

    @Test
    public void testStaticInnerClass() {
        OuterClass.StaticInnerClass sic = new OuterClass.StaticInnerClass(4);
        assertThat(sic.bar(5)).isEqualTo(14);
    }
}
