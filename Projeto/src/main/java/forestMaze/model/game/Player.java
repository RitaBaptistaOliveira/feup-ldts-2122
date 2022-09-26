package forestMaze.model.game;

import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.state.RoomState;

import java.util.ArrayList;

public class Player {
    private RoomState state;
    private ArrayList<HoldableItem> inventory;
    private Integer itemInHand;
    public Player(RoomState state){
        inventory = new ArrayList<>();
        this.state = state;
    }

    public RoomState getState(){
        return state;
    }
    public void setState(RoomState state){
        this.state = state;
    }

    public ArrayList<HoldableItem> getInventory(){
        return inventory;
    }

    public void addItem(HoldableItem item){
        inventory.add(item);
    }

    public Integer getItemInHand() {
        return itemInHand;
    }

    public void setItemInHand(Integer itemInHand) {
        this.itemInHand = itemInHand;
    }

    public String getNameOfItemInHand(){
        String name = "";
        for(HoldableItem item : inventory){
            if(itemInHand == item.getId()) name = item.getName();
        }
        return name;
    }

    public Integer existsInInventory(String itemName) {
        for (HoldableItem item : inventory){
            if(item.getName().equals(itemName)){
                return item.getId();
            }
        }
        return -1;
    }
}

