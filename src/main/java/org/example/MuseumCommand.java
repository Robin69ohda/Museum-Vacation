package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class MuseumCommand {
    public abstract void execute(Museum museum, ArrayList<Museum> museums, Path outputPath) throws IOException;
}
