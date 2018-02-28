package com.xyzcorp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsTest {


    public void processInvariant(List<Person> persons) {
        persons.add(new Person());
        persons.add(new Floridian());
        persons.add(new Tampan());
        persons.add(new Miamian());
        persons.add(new Floridian());
        Person person = persons.get(0);
        Object object = persons.get(0);
    }

    @Test
    public void testInvariantMethodCall() {
        List<Person> people = Arrays.asList(new Person(), new Person());
        processInvariant(people);
    }

    public void processCovariant(List<? extends Floridian> persons) {
        Object object = persons.get(0);
        Person person = persons.get(0);
        American american = persons.get(0);
        Floridian floridian = persons.get(0);
        //Miamian miamian = persons.get(0);
        //persons.add(new Object());
        //persons.add(new Person());
        //persons.add(new American());
        //persons.add(new Floridian());
        //persons.add(new Miamian());
        persons.add(null);
    }

    @Test
    public void testCovariantMethodCall() {
        List<Miamian> miamians = Arrays.<Miamian>asList(new Miamian(),
                new Miamian());
        processCovariant(miamians);
    }


    public void processContravariant(List<? super American> americans) {
        Object object = americans.get(0);
        //Person person = americans.get(0);
        //American american = americans.get(0);
        //Floridian floridian = americans.get(0);
        //Miamian miamian = americans.get(0);
        //americans.add(new Object());
        //americans.add(new Person());
        americans.add(new American());
        americans.add(new Floridian());
        americans.add(new Miamian());
    }


    @Test
    public void testContravariantMethodCall() {
        List<Person> personList = Arrays.asList(new Person(), new Person());
        processContravariant(personList);
    }


    @Test
    public void testAssignments() {
        List<? extends American> americansOrLower = new ArrayList<Miamian>();

        List<?> objectsOrLower = new ArrayList<Integer>();

    }
}
