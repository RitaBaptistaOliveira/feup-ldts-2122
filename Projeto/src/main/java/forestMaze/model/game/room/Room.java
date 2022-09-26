package forestMaze.model.game.room;
import java.util.ArrayList;

import forestMaze.controller.reader.ArtReader;
import forestMaze.controller.reader.RoomDescriptionReader;
import forestMaze.controller.reader.TextReader;
import forestMaze.model.CharPos;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.item.InteractableItem;

public class Room {
    private final String name;
    private final ArrayList<InteractableItem> interactableItems;
    private ArrayList<HoldableItem> holdableItems;
    private final ArrayList<CharPos> roomArt;
    private final Integer exitId;
    private final ArrayList<String> description;

    public Room(String name, ArrayList<InteractableItem> interactableItems, ArrayList<HoldableItem> holdableItems, Integer exitId) {
        this.name = name;
        this.interactableItems = interactableItems;
        this.holdableItems = holdableItems;
        this.roomArt = new ArtReader("rooms\\" + name).read();
        this.exitId = exitId;
        this.description = new RoomDescriptionReader("rooms\\" + name + "Description").read();
    }
    public String getName(){
        return name;
    }

    public ArrayList<InteractableItem> getInteractableItems() {
        return interactableItems;
    }

    public ArrayList< HoldableItem> getHoldableItems() {
        return holdableItems;
    }

    public void removeHoldableItem(HoldableItem item) {
        holdableItems.remove(item);
    }

    public ArrayList<CharPos> getRoomArt() {
        return this.roomArt;
    }

    public Integer getExitId() {
        return exitId;
    }

    public ArrayList<String> getDescription(){ return this.description;}
}