package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Objects;

public class GroupsManagement {
    String[] parts;
    Path outputPath;
    HashMap<GroupKey, Group> groups;
    GroupsManagement(String[] parts, Path outputPath, HashMap<GroupKey, Group> groups) {
        this.parts = parts;
        this.outputPath = outputPath;
        this.groups = groups;
    }
    public void start () throws IOException {
        Group mainGroup = new Group(Integer.parseInt(parts[9]), parts[10]);
        GroupKey groupKey = new GroupKey(Integer.parseInt(parts[9]), parts[10]);
        Person person = null;
        if (Objects.equals(parts[5], "")) {
            parts[5] = "null";
        }
        if (parts[3].equals("profesor")) {
            person = new Professor(parts[1], parts[2], parts[8]);
            ((Professor) person).setData(Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]));
        } else if (parts[3].equals("student")) {
            person = new Student(parts[1], parts[2], parts[8]);
            ((Student) person).setData(Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]));
        }
        if (Objects.equals(parts[0], "ADD GUIDE")) {
            if (!groups.containsKey(groupKey)) {
                groups.put(groupKey, mainGroup);
            } else {
                mainGroup = groups.get(groupKey);
            }
            try {
                AddGuide addGuide = new AddGuide();
                addGuide.execute(person, mainGroup, outputPath);
            } catch (GuideTypeException e) {
                String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (new guide: " + person + ")\n";
                Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (GuideExistsException e) {
                String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (new guide: " + mainGroup.getGuide().toString() + ")\n";
                Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } else if (Objects.equals(parts[0], "ADD MEMBER")) {
            if (!groups.containsKey(groupKey)) {
                try {
                    throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
                } catch (GroupNotExistsException e) {
                    String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (new member: " + person + ")\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            } else {
                mainGroup = groups.get(groupKey);
                AddMember addMember = new AddMember();
                try {
                    addMember.execute(person, mainGroup, outputPath);
                } catch (GuideExistsException | GuideTypeException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (Objects.equals(parts[0], "REMOVE MEMBER")) {
            if (!groups.containsKey(groupKey)) {
                try {
                    throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
                } catch (GroupNotExistsException e) {
                    String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (removed member: " + person + ")\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            } else {
                mainGroup = groups.get(groupKey);
                RemoveMember removeMember = new RemoveMember();
                try {
                    removeMember.execute(person, mainGroup, outputPath);
                } catch (GuideExistsException | GuideTypeException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (Objects.equals(parts[0], "REMOVE GUIDE")) {
            if (!groups.containsKey(groupKey)) {
                try {
                    throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
                } catch (GroupNotExistsException e) {
                    String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (removed member: " + person + ")\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            } else {
                groups.remove(groupKey);
                String result = parts[9] + " ## " + parts[10] + " ## removed guide: " + person + "\n";
                Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } else if (Objects.equals(parts[0], "FIND MEMBER")) {
            if (!groups.containsKey(groupKey)) {
                try {
                    throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
                } catch (GroupNotExistsException e) {
                    String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (find member: " + person + ")\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            } else {
                mainGroup = groups.get(groupKey);
                if (mainGroup.getMembers().contains(person)) {
                    String result = parts[9] + " ## " + parts[10] + " ## member found: " + person + "\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } else {
                    String result = parts[9] + " ## " + parts[10] + " ## member not exists: " + person + "\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            }
        } else if (Objects.equals(parts[0], "FIND GUIDE")) {
            if (person instanceof Professor) {
                if (!groups.containsKey(groupKey)) {
                    try {
                        throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
                    } catch (GroupNotExistsException e) {
                        String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (find guide: " + person + ")\n";
                        Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    }
                } else {
                    mainGroup = groups.get(groupKey);
                    if (mainGroup.getGuide().equals(person)) {
                        String result = parts[9] + " ## " + parts[10] + " ## guide found: " + person + "\n";
                        Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } else {
                        String result = parts[9] + " ## " + parts[10] + " ## guide not exists: " + person + "\n";
                        Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    }
                }
            } else if (person != null) {
                try {
                    throw new GuideTypeException("GuideTypeException: Guide must be a professor.");
                } catch (GuideTypeException e) {
                    String result = parts[9] + " ## " + parts[10] + " ## " + e.getMessage() + " ## (new guide: " + person + ")\n";
                    Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            }
        }
    }
}

