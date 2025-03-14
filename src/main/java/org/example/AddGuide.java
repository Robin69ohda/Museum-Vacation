package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AddGuide extends GroupAdditions{
    @Override
    public void execute(Person person, Group group, Path outputPath) throws IOException, GuideExistsException, GuideTypeException {
        if (person instanceof Student) {
            throw new GuideTypeException("GuideTypeException: Guide must be a professor.");
        }
        if (group.getGuide() != null) {
            throw new GuideExistsException("GuideExistsException: Guide already exists.");
        }
        Professor professor = (Professor)person;
        group.addGuide(professor);
        String result = group.getMuseumCode() + " ## " + group.getTimetable() + " ## new guide: " + professor.toString() + "\n";
        Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
