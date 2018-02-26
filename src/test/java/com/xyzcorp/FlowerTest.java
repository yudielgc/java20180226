package com.xyzcorp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlowerTest {

    @Test
    public void testBuildWithNoAttributes() {
        Flower f = Flower.builder().build();
        assertThat(f).isNotNull().isInstanceOf(Flower.class);
    }

    @Test
    public void testBuildWithPetals() {
        Flower f = Flower.builder().petals(2).build();
        assertThat(f).isNotNull().isInstanceOf(Flower.class);
        assertThat(f.getPetalCount()).isEqualTo(2);
    }

    @Test
    public void testBuildWithColorPetals() {
        Flower f = Flower.builder().color("Yellow").petals(2).build();
        assertThat(f).isNotNull().isInstanceOf(Flower.class);
        assertThat(f.getColor()).isEqualTo("Yellow");
        assertThat(f.getPetalCount()).isEqualTo(2);
    }

    @Test
    public void testBuildWithLatinNamePetals() {
        Flower f = Flower.builder()
                         .color("Yellow")
                         .petals(2)
                         .latinName("Narcissus")
                         .build();
        assertThat(f).isNotNull().isInstanceOf(Flower.class);
        assertThat(f.getColor()).isEqualTo("Yellow");
        assertThat(f.getPetalCount()).isEqualTo(2);
        assertThat(f.getLatinName()).isEqualTo("Narcissus");
    }

    @Test
    public void testBuildWithDifferentOrder() {
        Flower f = Flower.builder()
                         .latinName("Narcissus")
                         .petals(2)
                         .color("Yellow")
                         .build();
        assertThat(f).isNotNull().isInstanceOf(Flower.class);
        assertThat(f.getColor()).isEqualTo("Yellow");
        assertThat(f.getPetalCount()).isEqualTo(2);
        assertThat(f.getLatinName()).isEqualTo("Narcissus");
    }


    //TODO: Thread safety M@ don't like
}
