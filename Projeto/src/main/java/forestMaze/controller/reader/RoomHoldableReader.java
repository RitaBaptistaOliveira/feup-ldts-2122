package forestMaze.controller.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RoomHoldableReader extends Reader<ArrayList<String>> {
    private final String filename;

    public RoomHoldableReader(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<String> read() {

        ArrayList<String> attributeList = new ArrayList<>();
        System.out.println(filename);
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

            //Read the file line by line
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] attributes = line.split(DELIMITER);
                if (attributes[0].equals(SEPARATOR)) {
                    break;
                }
                else {
                    attributeList.add(attributes[0]);
                    attributeList.add(attributes[1]);
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
