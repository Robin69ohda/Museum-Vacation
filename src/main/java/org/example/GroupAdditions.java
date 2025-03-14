package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class GroupAdditions {
    public abstract void execute(Person person, Group group, Path outputPath) throws IOException, GuideExistsException, GuideTypeException;
}
