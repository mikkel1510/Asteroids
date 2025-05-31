package dk.sdu.cbse.playersystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.commonplayer.Player;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }


    private Entity createPlayer(GameData gameData) {
        Player player = new Player();
        player.setPolygonCoordinates(-7.5,-7.5, 15,0, -7.5,7.5);
        player.setX(gameData.getDisplayHeight()/2);
        player.setY(gameData.getDisplayWidth()/2);
        player.setRadius(12);
        player.setHealth(10);
        player.setMaxHealth(10);
        player.setFireRate(250);
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
