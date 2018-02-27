package com.xyzcorp;

public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES(true);

    private boolean isTrumps;

    Suit() {
        this(false);
    }

    Suit(boolean isTrumps) {
        this.isTrumps = isTrumps;
    }

    public void setTrumps(boolean trumps) {
        isTrumps = trumps;
    }

    public String toPrettyName() {
        StringBuilder rv = new StringBuilder(
                name().toLowerCase());
        rv.setCharAt(0,
                Character.toUpperCase(rv.charAt(0)));
        if (isTrumps) {
            rv.append(" (Trumps)");
        }
        return rv.toString();
    }
}
