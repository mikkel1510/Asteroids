package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.GameKeys;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.commonplayer.Player;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 3);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 3);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
                world.setPlayerX(player.getX());
                world.setPlayerY(player.getY());
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE) && System.currentTimeMillis() - player.getLastFired() > player.getFireRate()) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createProjectile(player, gameData))
                );
                player.setLastFired(System.currentTimeMillis());;
            }
        }
    }

    private Collection<? extends ProjectileSPI> getBulletSPIs(){
        return ServiceLoader.load(ProjectileSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
