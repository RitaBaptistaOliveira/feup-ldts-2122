package forestMaze.model.loaders;

import forestMaze.controller.reader.RoomHoldableReader;
import forestMaze.model.game.item.HoldableItem;

import java.util.ArrayList;

public class HoldableItemsLoader {
    private RoomHoldableReader reader;

    public HoldableItemsLoader(Integer roomNumber){
        this.reader = new RoomHoldableReader("rooms\\room" + roomNumber + "Description");
    }

    public ArrayList<HoldableItem> loadRoomHoldables(){
        ArrayList<HoldableItem> holdableItemsList = new ArrayList<>();

        ArrayList<String> itemAttributes = reader.read();

        for (int i = 0; i < itemAttributes.size(); i+=2)
        {
            if (itemAttributes.get(i+1).equals("0"))
                holdableItemsList.add(new HoldableItem(itemAttributes.get(i), false));
            else
                holdableItemsList.add(new HoldableItem(itemAttributes.get(i), true));
        }
        return holdableItemsList;
    }
}
