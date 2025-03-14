package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class AddMuseum extends MuseumCommand {
    @Override
    public void execute(Museum museum, ArrayList<Museum> museums, Path outputPath) throws IOException {
        museums.add(museum);
        String result = museum.getCode() + ": " + museum.getName() + "\n";
        Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
