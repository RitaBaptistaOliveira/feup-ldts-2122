package forestMaze.model.loaders;

import forestMaze.model.game.item.*;
import forestMaze.model.game.room.Room;

import java.util.ArrayList;

public class RoomLoader {

    private final Integer roomNumber;
    private HoldableItemsLoader holdableItems;
    private InteractableItemsLoader interactableItems;

    public RoomLoader(Integer roomNumber){
        this.roomNumber = roomNumber;
        holdableItems = new HoldableItemsLoader(roomNumber);
        interactableItems = new InteractableItemsLoader(roomNumber);
    }

    public Room loadRoom(){
        ArrayList<HoldableItem> holdableItems = this.holdableItems.loadRoomHoldables();
        ArrayList<InteractableItem> interactableItems = this.interactableItems.loadRoomInteractables();
        return new Room("room" + roomNumber, interactableItems, holdableItems, Integer.parseInt(this.interactableItems.loadRoomExit()));
    }

}
