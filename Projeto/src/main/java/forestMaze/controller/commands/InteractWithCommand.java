package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.item.InteractableItem;

public class InteractWithCommand extends Command{

    public InteractWithCommand(Player player, String itemName, GameText gameText, GameArt gameArt) {
        super(player, itemName, gameText, gameArt);
    }

    @Override
    public void execute(){
        if (itemName.equals("")){
            gameText.setActionLines("You need to write an item!");
            return;
        }
        for(InteractableItem item : player.getState().getState().getInteractableItems()){
            if(item.getName().equals(itemName)){
                if (item.getInteractWithItemId() == -1) {
                    if (!item.isUsed()) {
                        item.setUsed(true);
                        for(HoldableItem holdableItem : player.getState().getState().getHoldableItems()){
                            if(holdableItem.getId() == item.getHoldableItemId()){
                                holdableItem.setAvailable(true);
                            }
                        }
                        gameText.setActionLines(item.getInteractionDescription());
                        gameArt.setArt(item.getArt());
                        if(item.getId() == player.getState().getState().getExitId())
                            player.getState().setCanExit(true);
                    }
                    else {
                        gameText.setActionLines("You can't use that " + itemName + " any more.");
                    }
                    return;
                }
            }
        }
        gameText.setActionLines("You tried to do something with the " + itemName + ", but nothing happened...");
    }
}
