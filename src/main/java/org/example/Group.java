package org.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Person> members;
    private Professor guide;
    private final Integer museumCode;
    private final String timetable;
    public Group(Integer museumCode, String timetable) {
        this.museumCode = museumCode;
        this.timetable = timetable;
        members = new ArrayList<Person>();
    }
    public void addGuide(Professor professor) throws GuideExistsException {
        if (guide != null) {
            throw new GuideExistsException("GuideExistsException: Guide already exists.");
        }
        guide = professor;
    }
    public void addMember(Person person) throws GroupThresholdException {
        if (members.size() == 10) {
            throw new GroupThresholdException("GroupThresholdException: Group cannot have more than 10 members.");
        }
        members.add(person);
    }
    public void removeMember(Person person) throws PersonNotExistsException {
        if (!members.contains(person)) {
            System.out.println("Members do not exist.");
            throw new PersonNotExistsException("PersonNotExistsException: Person was not found in the group.");
        }
        members.remove(person);
    }
    public void resetGuide() {
        guide = null;
    }
    public List<Person> getMembers() {
        return members;
    }
    public Professor getGuide() {
        return guide;
    }
    public Integer getMuseumCode() {
        return museumCode;
    }
    public String getTimetable() {
        return timetable;
    }
}
