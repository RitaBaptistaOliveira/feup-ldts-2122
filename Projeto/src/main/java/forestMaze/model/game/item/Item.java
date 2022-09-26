package forestMaze.model.game.item;

import forestMaze.model.CharPos;

import java.util.ArrayList;

public interface Item {
    int getId();
    String getName();
    ArrayList<CharPos> getArt();
    String getDescription();
}
