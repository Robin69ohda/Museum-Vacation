package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Professor extends Person {
    private String school;
    private int experience;
    public Professor(String surname, String name, String role) {
        super(surname, name, role);
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setData(int age, String email, String school, int experience) {
        this.setAge(age);
        this.setEmail(email);
        this.setSchool(school);
        this.setExperience(experience);
    }
    public void update(String event, Path outputPathEvents, String name, long code) throws IOException {
        String result = "To: " + this.getEmail() + " ## Message: " + name + " (" + code + ") " + event + "\n";
        Files.write(outputPathEvents, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    @Override
    public String toString() {
        return "surname=" + this.getSurname() + ", name=" + this.getName() + ", role=" + this.getRole() + ", age=" +
                this.getAge() + ", email=" + this.getEmail() + ", school=" + school +", experience=" + experience;
    }
}
