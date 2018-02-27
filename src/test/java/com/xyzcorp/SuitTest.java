package com.xyzcorp;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuitTest {

    @Test
    public void testSuitPrettyName() {
        System.out.println(Suit.CLUBS.toPrettyName());
        System.out.println(Suit.SPADES.toPrettyName());
        //Suit.CLUBS.setTrumps(true); NOT A GOOD IDEA
        System.out.println(Suit.CLUBS.toPrettyName());
    }
}