package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AddMember extends GroupAdditions{
    @Override
    public void execute(Person person, Group group, Path outputPath) throws IOException, GuideExistsException, GuideTypeException {
        try {
            group.addMember(person);
            String result = group.getMuseumCode() + " ## " + group.getTimetable() + " ## new member: " + person + "\n";
            Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (GroupThresholdException e) {
            String result = group.getMuseumCode() + " ## " + group.getTimetable() + " ## " + e.getMessage() + " ## (new member: " + person + ")\n";
            Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }
}
