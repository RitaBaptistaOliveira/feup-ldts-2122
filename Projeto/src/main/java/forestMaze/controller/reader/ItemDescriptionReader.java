package forestMaze.controller.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;

public class ItemDescriptionReader extends Reader<String> {
    private final String filename;

    public ItemDescriptionReader(String filename) {
        this.filename = filename;
    }

    @Override
    public String read() {
        String description = "";
        URL resource = getClass().getClassLoader().getResource(removeSpaces(filename) + ".txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        BufferedReader fileReader = null;

        try {
            String line;
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));

            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                description = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return description;
    }
}