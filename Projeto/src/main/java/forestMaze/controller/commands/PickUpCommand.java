package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.HoldableItem;

public class PickUpCommand extends Command {

    public PickUpCommand(Player player, String itemName, GameText gameText, GameArt gameArt) {
        super(player, itemName, gameText, gameArt);
    }

    @Override
    public void execute() {
        if (itemName.equals("")){
            gameText.setActionLines("You need to write an item!");
            return;
        }
        for (HoldableItem item : player.getState().getState().getHoldableItems()) {
            if (item.getName().equals(itemName) && item.getAvailable()) {
                player.addItem(item);
                player.getState().getState().removeHoldableItem(item);
                gameText.setActionLines("You picked up the " + itemName + "!");
                gameArt.setArt(item.getArt());
                return;
            }
        }
        gameText.setActionLines("The " + itemName + " can't be picked up...");
    }
}