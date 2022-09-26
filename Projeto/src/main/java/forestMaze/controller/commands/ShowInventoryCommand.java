package forestMaze.controller.commands;


import forestMaze.model.game.Player;
import forestMaze.model.game.gameText.GameArt;
import forestMaze.model.game.gameText.GameText;
import forestMaze.model.game.item.HoldableItem;

public class ShowInventoryCommand extends Command{

    public ShowInventoryCommand(Player player, GameText gameText, GameArt gameArt){
        super(player, gameText, gameArt);
    }

    @Override
    public void execute() {
        StringBuilder output = new StringBuilder();
        for(HoldableItem item : player.getInventory()){
            output.append(item.getName()).append(" ");
        }
        gameText.setActionLines(output.toString());
        gameArt.setArt(player.getState().getState().getRoomArt());
    }
}
