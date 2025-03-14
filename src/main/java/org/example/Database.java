package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Database {
    private static Database database;
    private Set<Museum> museums;
    private Set<Group> groups;
    private Database() {
        this.museums = new HashSet<>();
        this.groups = new HashSet<>();
    }
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
    public void addMuseum(Museum museum) {
        museums.add(museum);
    }
    public void addMuseums(ArrayList<Museum> museums) {
        this.museums.addAll(museums);
    }
    public void addGroup(Group group) {
        groups.add(group);
    }
    public void addGroups(HashMap<GroupKey, Group> groups) {
        this.groups.addAll(groups.values());
    }
    public Set<Museum> getMuseums() {
        return museums;
    }
    public Set<Group> getGroups() {
        return groups;
    }
}
