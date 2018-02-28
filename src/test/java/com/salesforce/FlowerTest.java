package com.salesforce;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlowerTest {

    @Test
    public void testBuildWithNoAttributes() throws Exception {
        Flower flower = Flower.builder().build();

        assertThat(flower).isNotNull().isInstanceOf(Flower.class);
    }

    @Test
    public void testBuildWithPetals() throws Exception {
        Flower flower = Flower.builder().petals(2).build();

        assertThat(flower).isNotNull().isInstanceOf(Flower.class);
        assertThat(flower.getPetalCount()).isEqualTo(2);
    }
}
