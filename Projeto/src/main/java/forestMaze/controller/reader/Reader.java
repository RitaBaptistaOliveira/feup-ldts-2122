package forestMaze.controller.reader;

import java.util.ArrayList;

public abstract class Reader<T> {
    abstract T read();

    protected String removeSpaces(String filename){
        StringBuilder newFilename = new StringBuilder();
        for(int i = 0; i < filename.length(); i++){
            if(filename.charAt(i) != ' '){
                newFilename.append(filename.charAt(i));
            }
        }
        return newFilename.toString();
    }
}
