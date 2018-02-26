package com.xyzcorp;


public class Flower {
	private final int petals;
	private final String color;
	private final String latinName;

	public static FlowerBuilder builder() {
		return new FlowerBuilder();
	}

	private Flower(int petals, String color, String latinName) {
		this.petals = petals;
		this.color = color;
		this.latinName = latinName;
	}

	public int getPetalCount() {
		return petals;
	}

	public String getColor() {
		return color;
	}

	public String getLatinName() {
		return latinName;
	}

	public static class FlowerBuilder {
		private int petals;
		private String color;
		private String latinName;

		public Flower build() {
			return new Flower(petals, color, latinName);
		}

		public FlowerBuilder petals(int petals) {
			this.petals = petals;
			return this;
		}

		public FlowerBuilder color(String color) {
			this.color = color;
			return this;
		}

		public FlowerBuilder latinName(String latinName) {
			this.latinName = latinName;
			return this;
		}
	}
}
