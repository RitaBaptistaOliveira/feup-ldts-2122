package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.item.HoldableItem;
import forestMaze.model.game.item.InteractableItem;
import forestMaze.model.game.gameText.GameText;

public class LookAtCommand extends Command {

    public LookAtCommand(Player player, String itemName, GameText gameText, GameArt gameArt) {
        super(player, itemName, gameText, gameArt);
    }

    @Override
    public void execute() {
        if (itemName.equals("")){
            gameText.setActionLines("You need to write an item!");
            return;
        }
        for (HoldableItem holdableItem : player.getState().getState().getHoldableItems()) {
            if (holdableItem.getName().equals(itemName)) {
                gameText.setActionLines(holdableItem.getDescription());
                gameArt.setArt(holdableItem.getArt());
                return;
            }
        }
        for (InteractableItem interactableItem : player.getState().getState().getInteractableItems()) {
            if (interactableItem.getName().equals(itemName)) {
                gameText.setActionLines(interactableItem.getDescription());
                gameArt.setArt(interactableItem.getArt());
                return;
            }
        }
        gameText.setActionLines("There's nothing called " + itemName);
    }
}