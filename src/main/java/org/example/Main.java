package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = args[1] + ".in";
        String outputFile = args[1] + ".out";
        Path outputPath = Paths.get(outputFile);
        Path inputPath = Paths.get(inputFile);
        BufferedReader br = Files.newBufferedReader(inputPath);
        String format = br.readLine();
        Database dataBase = Database.getInstance();
        switch (args[0]) {
            case "museums" -> {
                ArrayList<Museum> museums = new ArrayList<>();
                String[] data = format.split("\\|");
                int dataSize = data.length;
                while (br.ready()) {
                    String line = br.readLine();
                    String[] parts = line.split("\\|");
                    if (parts.length != dataSize) {
                        Files.write(outputPath, ("Exception: Data is broken. ## (" + line + ")\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } else {
                        AddMuseum addMuseum = new AddMuseum();
                        Location location = new Location(parts[4], Integer.parseInt(parts[16]));
                        Museum museum = new Museum.Builder(parts[2], Long.parseLong(parts[1]), Long.parseLong(parts[14]), location)
                                .manager(parts[13])
                                .foundingYear(parts[10])
                                .phoneNumber(parts[8])
                                .fax(parts[9])
                                .email(parts[12])
                                .url(parts[11])
                                .profile(parts[15]).build();
                        addMuseum.execute(museum, museums, outputPath);
                    }
                }
                dataBase.addMuseums(museums);
            }
            case "groups" -> {
                HashMap<GroupKey, Group> groups = new HashMap<>();
                while (br.ready()) {
                    String line = br.readLine();
                    String[] parts = line.split("\\|");
                    GroupsManagement groupsManagement = new GroupsManagement(parts, outputPath, groups);
                    groupsManagement.start();
                }
            }
            case "listener" -> {
                String inputFileGroups = args[2] + ".in";
                String outputFileGroups = args[2] + ".out";
                Path outputPathGroups = Paths.get(outputFileGroups);
                Path inputPathGroups = Paths.get(inputFileGroups);
                String inputFileEvents = args[3] + ".in";
                String outputFileEvents = args[3] + ".out";
                Path outputPathEvents = Paths.get(outputFileEvents);
                Path inputPathEvents = Paths.get(inputFileEvents);
                ArrayList<Museum> museums = new ArrayList<>();
                String[] data = format.split("\\|");
                int dataSize = data.length;
                while (br.ready()) {
                    String line = br.readLine();
                    String[] parts = line.split("\\|");
                    if (parts.length != dataSize) {
                        Files.write(outputPath, ("Exception: Data is broken. ## (" + line + ")\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } else {
                        AddMuseum addMuseum = new AddMuseum();
                        Location location = new Location(parts[4], Integer.parseInt(parts[16]));
                        Museum museum = new Museum.Builder(parts[2], Long.parseLong(parts[1]), Long.parseLong(parts[14]), location)
                                .manager(parts[13])
                                .foundingYear(parts[10])
                                .phoneNumber(parts[8])
                                .fax(parts[9])
                                .email(parts[12])
                                .url(parts[11])
                                .profile(parts[15]).build();
                        addMuseum.execute(museum, museums, outputPath);
                    }
                }
                dataBase.addMuseums(museums);
                BufferedReader brGroups = Files.newBufferedReader(inputPathGroups);
                String formatGroups = brGroups.readLine();
                HashMap<GroupKey, Group> groups = new HashMap<>();
                while (brGroups.ready()) {
                    String line = brGroups.readLine();
                    String[] parts = line.split("\\|");
                    GroupsManagement groupsManagement = new GroupsManagement(parts, outputPathGroups, groups);
                    groupsManagement.start();
                }
                dataBase.addGroups(groups);
                for (Group group : dataBase.getGroups()) {
                    for (Museum museum : museums) {
                        if (museum.getCode() == group.getMuseumCode()) {
                            museum.guides.add(group.getGuide());
                            break;
                        }
                    }
                }
                BufferedReader brEvents = Files.newBufferedReader(inputPathEvents);
                String formatEvents = brEvents.readLine();
                while (brEvents.ready()) {
                    String line = brEvents.readLine();
                    String[] parts = line.split("\\|");
                    long code = Long.parseLong(parts[1]);
                    for (Museum museum : museums) {
                        if (museum.getCode() == code) {
                            museum.notifyGuide(parts[2], outputPathEvents);
                            break;
                        }
                    }
                }
            }
        }
    }
}
