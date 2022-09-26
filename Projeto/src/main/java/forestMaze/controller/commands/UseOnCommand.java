package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.item.InteractableItem;

public class UseOnCommand extends Command {

    public UseOnCommand(Player player, String itemName, GameText textOutput, GameArt gameArt) {
        super(player, itemName, textOutput, gameArt);
    }

    @Override
    public void execute() {
        if (itemName.equals("")){
            gameText.setActionLines("You need to write an item!");
            return;
        }
        for (InteractableItem interactableItem : player.getState().getState().getInteractableItems()) {
            if (interactableItem.getName().equals(itemName)) {
                if(interactableItem.isUsed()){
                    gameText.setActionLines("You can't do anything else with the " + itemName + ".");
                    return;
                }
                if(interactableItem.getInteractWithItemId().equals(player.getItemInHand())) {
                    if(interactableItem.getId() == player.getState().getState().getExitId()){
                        player.getState().setCanExit(true);
                        gameText.setActionLines(interactableItem.getInteractionDescription());
                        gameArt.setArt(interactableItem.getArt());
                        return;
                    }
                    for(HoldableItem item : player.getState().getState().getHoldableItems()){
                        if(item.getId() == interactableItem.getHoldableItemId()){
                            interactableItem.setUsed(true);
                            item.setAvailable(true);
                            gameText.setActionLines(interactableItem.getInteractionDescription());
                            gameArt.setArt(interactableItem.getArt());
                            return;
                        }
                    }
                }
                gameText.setActionLines("You tried to use the " + player.getNameOfItemInHand() + " on the " + itemName
                        + ", but nothing happened...");
                return;
            }
        }
        gameText.setActionLines("You tried to do something with the " + itemName + ", but nothing happened...");
    }
}
