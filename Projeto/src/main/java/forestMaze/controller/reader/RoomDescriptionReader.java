package forestMaze.controller.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RoomDescriptionReader extends Reader<ArrayList<String>> {
    private final String filename;

    public RoomDescriptionReader(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<String> read() {
        ArrayList<String> stringLines = new ArrayList<>();
        int stringLength = 100;

        URL resource = getClass().getClassLoader().getResource(removeSpaces(filename)+".txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        final String SEPARATOR = "---";

        BufferedReader fileReader = null;

        try {
            String line;
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));

            //Skip lines
            while ((line = fileReader.readLine()) != null) {
                if (line.equals(SEPARATOR)) {
                    break;
                }
            }
            while ((line = fileReader.readLine()) != null) {
                if (line.equals(SEPARATOR)) {
                    break;
                }
            }

            int firstChar;
            while ((firstChar = fileReader.read()) != -1) {
                StringBuilder newLine = new StringBuilder();
                newLine.append((char) firstChar);
                if (newLine.charAt(0) == ' ')
                    newLine.deleteCharAt(0);
                for (int x = 0; x < stringLength; x++) {
                    newLine.append((char) fileReader.read());
                    if (newLine.charAt(newLine.length()-1) == '\n')
                        break;
                    else if ((x == 0) && newLine.charAt(newLine.length()-1) == ' ') {
                        x--;
                        newLine.deleteCharAt(newLine.length()-1);
                    }
                }
                stringLines.add(newLine.toString());
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
        return stringLines;
    }
}