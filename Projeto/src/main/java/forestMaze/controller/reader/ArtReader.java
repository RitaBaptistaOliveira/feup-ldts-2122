package forestMaze.controller.reader;

import forestMaze.model.CharPos;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ArtReader extends Reader<ArrayList<CharPos>> {
    private final String filename;

    public ArtReader(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<CharPos> read() {
        ArrayList<CharPos> characters = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(removeSpaces(filename) + ".txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));
            int y = 0;
            while ((line = fileReader.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) != '\t' && line.charAt(x) != '\n' && line.charAt(x) != ' ') {
                        CharPos character = new CharPos(x, y, line.charAt(x));
                        characters.add(character);
                    }
                }
                y++;
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
        return characters;
    }
}