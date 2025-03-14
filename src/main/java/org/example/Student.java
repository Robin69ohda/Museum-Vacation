package org.example;

public class Student extends Person {
    private String school;
    private int studyYear;
    public Student(String surname, String name, String role) {
        super(surname, name, role);
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public int getStudyYear() {
        return studyYear;
    }
    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }
    public void setData(int age, String email, String school, int studyYear) {
        this.setAge(age);
        this.setEmail(email);
        this.setSchool(school);
        this.setStudyYear(studyYear);
    }
    @Override
    public String toString() {
        return "surname=" + this.getSurname() + ", name=" + this.getName() + ", role=" + this.getRole() + ", age=" +
                this.getAge() + ", email=" + this.getEmail() + ", school=" + school +", studyYear=" + studyYear;
    }
}
