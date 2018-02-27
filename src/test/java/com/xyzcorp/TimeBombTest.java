package com.xyzcorp;

import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;

public class TimeBombTest {
    @Test
    public void testTwoBadThingsHappening() {
        TimeBomb timeBomb = new TimeBomb();
        try {
            timeBomb.thisWillThrowAIOException();
            timeBomb.thisWillThrowACustomException();
        } catch (IOException | MyCheckedException e) {
            e.printStackTrace();
        }
    }

    //Old-Style
    @Test
    public void testAnException() {
        TimeBomb timeBomb = new TimeBomb();
        try {
            timeBomb.thisWillThrowACustomException();
            fail("This should fail");
        } catch (MyCheckedException exception) {
            assertThat(exception).hasMessage("I don't feel well");
        }
    }

    @Test(expected = MyCheckedException.class)
    public void testAnExceptionWithJunitAnnotations() throws
            MyCheckedException {
        TimeBomb timeBomb = new TimeBomb();
        timeBomb.thisWillThrowACustomException();
    }

    @Rule
    public ExpectedException exp = ExpectedException.none();

    @Test
    public void testAnExceptionUsingJUnitRules() throws Exception {
        exp.expectMessage("I don't feel well");
        exp.expect(MyCheckedException.class);
        TimeBomb timeBomb = new TimeBomb();
        timeBomb.thisWillThrowACustomException();
    }

    @Test
    public void testAnExceptionWithAssertJ() {
        TimeBomb timeBomb = new TimeBomb();
        assertThatThrownBy(timeBomb::thisWillThrowACustomException)
                .hasMessage("I don't feel well")
                .isInstanceOf(MyCheckedException.class);
    }
}




