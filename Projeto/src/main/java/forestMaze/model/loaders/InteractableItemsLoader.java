package forestMaze.model.loaders;

import forestMaze.controller.reader.RoomHoldableReader;
import forestMaze.controller.reader.RoomInteractableReader;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.item.InteractableItem;

import java.util.ArrayList;

public class InteractableItemsLoader {
    private RoomInteractableReader reader;

    public InteractableItemsLoader(Integer roomNumber) {
        this.reader = new RoomInteractableReader("rooms\\room" + roomNumber + "Description");
    }

    public String loadRoomExit() {
        return reader.readExit();
    }

    public ArrayList<InteractableItem> loadRoomInteractables(){
        ArrayList<InteractableItem> interactableItemsList = new ArrayList<>();

        ArrayList<String> itemAttributes = reader.read();

        for (int i = 0; i < itemAttributes.size(); i+=3) {
            System.out.println(itemAttributes.get(i));
            System.out.println(itemAttributes.get(i+1));
            System.out.println(itemAttributes.get(i+2));

            interactableItemsList.add(new InteractableItem(itemAttributes.get(i), Integer.parseInt(itemAttributes.get(i+1)), Integer.parseInt(itemAttributes.get(i+2))));
        }

        return interactableItemsList;
    }
}