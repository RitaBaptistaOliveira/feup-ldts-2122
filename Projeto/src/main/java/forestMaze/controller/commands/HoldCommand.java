package forestMaze.controller.commands;

import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;

public class HoldCommand extends Command {

    public HoldCommand(Player player, String itemName, GameText gameText, GameArt gameArt) {
        super(player, itemName, gameText, gameArt);
    }

    @Override
    public void execute() {
        if (itemName.equals("")){
            gameText.setActionLines("You need to write an item!");
            return;
        }
        Integer id = player.existsInInventory(itemName);
        if (id != -1) {
            System.out.println("id: " + id);
            player.setItemInHand(id);
            gameText.setActionLines("You're now holding the " + itemName + ".");
            gameArt.setArt(player.getState().getState().getRoomArt());
        } else {
            gameText.setActionLines("The " + itemName + " is not in your inventory!");
        }
    }
}