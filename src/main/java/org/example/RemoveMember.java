package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RemoveMember extends GroupAdditions{
    @Override
    public void execute(Person person, Group group, Path outputPath) throws IOException, GuideExistsException, GuideTypeException {
        try {
            group.removeMember(person);
            String result = group.getMuseumCode() + " ## " + group.getTimetable() + " ## removed member: " + person + "\n";
            Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (PersonNotExistsException e) {
            String result = group.getMuseumCode() + " ## " + group.getTimetable() + " ## " + e.getMessage() + " ## (" + person + ")\n";
            Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }
}
