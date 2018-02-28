package com.salesforce;

import org.junit.Test;
;
import static org.assertj.core.api.Assertions.assertThat;

public class OuterClassTest {

    @Test
    public void testInnerClass() throws Exception {
        OuterClass.InnerClass oic = new OuterClass().new InnerClass(2);
        assertThat(oic.foo(5)).isEqualTo(8);
    }

    @Test
    public void testTwoInnerClasses() throws Exception {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass oic1 = outerClass.new InnerClass(10);
        OuterClass.InnerClass oic2 = outerClass.new InnerClass(12);

        assertThat(oic1.foo(5)).isEqualTo(16);
        assertThat(oic2.foo(5)).isEqualTo(18);
    }

    @Test
    public void testStaticInnerClass() throws Exception {
        OuterClass.StaticInnerClass sic = new OuterClass.StaticInnerClass(4);

        assertThat(sic.bar(5)).isEqualTo(14);
    }
}
