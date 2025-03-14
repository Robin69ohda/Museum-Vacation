package org.example;

import java.util.Objects;

public class Person {
    private final String surname;
    private final String name;
    private String role;
    private int age;
    private String email;
    public Person(String surname, String name, String role) {
        this.surname = surname;
        this.name = name;
        this.role = role;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object obj) {
        // Check for reference equality
        if (this == obj) {
            return true;
        }

        // Check if the object is of the same type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast and compare fields
        Person person = (Person) obj;
        return Objects.equals(surname, person.surname) &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name);
    }
}
