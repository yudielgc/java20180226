package com.xyzcorp;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    @Test
    public void testEmployeeWithASet() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee("Jimi", "Hendrix"));
        employees.add(new Employee("Jimi", "Hendrix"));
        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    public void testEmployeeWithASetWithMutability() {
        Set<Employee> employees = new HashSet<>();
        Employee hendrix = new Employee("Jimi", "Hendrix");
        employees.add(hendrix);
        Employee hendrix2 = new Employee("Jimi", "Hendrix");
        employees.add(hendrix2);
        hendrix2.setFirstName("Jimmy");
        hendrix2.setLastName("Page");
        assertThat(employees.size()).isEqualTo(1);
        System.out.println(employees);
    }
}
