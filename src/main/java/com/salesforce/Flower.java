package com.salesforce;

public class Flower {

    private final int petals;

    public Flower(int petals) {
        this.petals = petals;
    }

    public static FlowerBuilder builder() {
        return new FlowerBuilder();
    }

    public int getPetalCount() {
        return petals;
    }

    public static class FlowerBuilder {

        private int petals;

        public Flower build() {
            return new Flower(2);
        }

        public FlowerBuilder petals(int petals) {
            this.petals = petals;
            return new FlowerBuilder();
        }
    }
}
