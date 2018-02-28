package com.salesforce;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsTest {

    @Test
    public void testInvariantMethodCall() throws Exception {
        List<Person> people = Arrays.asList(new Person(), new Person());
        processInvariant(people);
    }

    @Test
    public void testCovariantMethodCall() throws Exception {
        List<Miamian> miamians = Arrays.asList(new Miamian(), new Miamian());
        processCovariant(miamians);
    }

    @Test
    public void testContravariantMethodCall() throws Exception {
        List<Person> personList = Arrays.asList(new Person(), new Person());
        processCovariant(personList);
    }

    private void processInvariant(List<Person> persons) {
        persons.add(new Person());
        persons.add(new Floridian());
        persons.add(new Tampan());
        persons.add(new Miamian());
        persons.add(new Floridian());

        Person person = persons.get(0);
        Object object = persons.get(0);
    }

    private void processCovariant(List<? extends Person> persons) {
        Person person = persons.get(0);
        Object object = persons.get(0);
    }

    private void processContravariant(List<American> americans) {

    }
}
