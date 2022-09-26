package forestMaze.controller.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RoomInteractableReader extends Reader<ArrayList<String>> {
    private final String filename;

    public RoomInteractableReader(String filename) {
        this.filename = filename;
    }

    public String readExit() {
        ArrayList<String> attributeList = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(removeSpaces(filename) + ".txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        final String DELIMITER = ",";
        final String SEPARATOR = "---";

        BufferedReader fileReader = null;

        String line = "";
        try {
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));
            return fileReader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public ArrayList<String> read() {

        ArrayList<String> attributeList = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(removeSpaces(filename) + ".txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        final String DELIMITER = ",";
        final String SEPARATOR = "---";

        BufferedReader fileReader = null;

        try {
            String line;
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));

            //Skip lines
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                if (line.equals(SEPARATOR)) {
                    break;
                }
            }

            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                String[] attributes = line.split(DELIMITER);
                if (attributes[0].equals(SEPARATOR)) {
                    break;
                }
                else {
                    attributeList.add(attributes[0]);
                    attributeList.add(attributes[1]);
                    attributeList.add(attributes[2]);
                }
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
        return attributeList;
    }
}
