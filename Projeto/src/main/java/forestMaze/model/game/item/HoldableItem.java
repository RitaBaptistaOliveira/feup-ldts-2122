package forestMaze.model.game.item;

import forestMaze.controller.reader.ArtReader;
import forestMaze.controller.reader.ItemDescriptionReader;
import forestMaze.model.CharPos;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HoldableItem implements Item {
    private static AtomicInteger uniqueId = new AtomicInteger();

    private final int itemID;
    private final String name;
    private final ArrayList<CharPos> art;
    private final String description;
    private boolean available;

    public HoldableItem(String name, boolean available) {
        this.name = name;
        itemID = uniqueId.getAndIncrement();
        this.available = available;
        this.art = new ArtReader("rooms\\art\\" + name).read();
        this.description = new ItemDescriptionReader("rooms\\descriptions\\" + name + "Description").read();
    }

    public int getId() { return this.itemID; }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public ArrayList<CharPos> getArt() { return this.art; }

    public boolean getAvailable() { return this.available; }

    public void setAvailable(boolean available) { this.available = available;}
}