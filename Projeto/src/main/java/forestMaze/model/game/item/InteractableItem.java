package forestMaze.model.game.item;

import forestMaze.controller.reader.ArtReader;
import forestMaze.controller.reader.ItemDescriptionReader;
import forestMaze.model.CharPos;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InteractableItem implements Item {
    private static AtomicInteger uniqueId = new AtomicInteger();

    private final int itemID;
    private final String name;
    private final ArrayList<CharPos> art;
    private final String description;
    private final String interactionDescription;
    private boolean used;
    private final Integer holdableItemId;
    private final Integer interactWithItemId;

    public InteractableItem(String name, Integer holdableItemId, Integer interactWithItemId) {
        this.name = name;
        itemID = uniqueId.getAndIncrement();
        this.art = new ArtReader("rooms\\art\\" + name).read();
        this.description = new ItemDescriptionReader("rooms\\descriptions\\" + name + "Description").read();
        used = false;
        this.holdableItemId = holdableItemId;
        this.interactWithItemId = interactWithItemId;

        if(holdableItemId != -1 || interactWithItemId != -1){
            this.interactionDescription = new ItemDescriptionReader("rooms\\descriptions\\interactionDescriptions\\" + name + "InteractionDescription").read();
        } else interactionDescription = "";
    }

    public int getId() { return this.itemID; }

    public String getName() { return this.name; }

    public ArrayList<CharPos> getArt() { return this.art; }

    public String getDescription() { return this.description; }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Integer getHoldableItemId() {
        return holdableItemId;
    }

    public Integer getInteractWithItemId() {return interactWithItemId; }

    public String getInteractionDescription() {return interactionDescription; }
}