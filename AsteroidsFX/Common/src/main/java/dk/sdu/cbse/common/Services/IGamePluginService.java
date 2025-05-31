package dk.sdu.cbse.common.Services;

import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;


/**
 * Service provider interface for game plugins responsible for the creation and removal of entities
 * <p>
 *     Implementations of this interface allow dynamic addition and removal of game elements at runtime
 * </p>
 */
public interface IGamePluginService {
    /**
     * Initializes and adds entities to the game world
     *
     * <p><b>Pre-condition:</b> The game data and world must be initialized and not null</p>
     * <p><b>Post-condition:</b> The plugin's entities have been created and added to the world</p>
     *
     * @param gameData the current game data (never null)
     * @param world the game world from to which entities should be added (never null)
     */
    void start(GameData gameData, World world);

    /**
     * Removes the plugin's entities from the game world and performs any necessary clean up
     *
     * <p><b>Pre-condition:</b> The plugin's entities are present in the world; the world is initialized</p>
     * <p><b>Post-condition:</b> The plugin's entities have been removed from the world</p>
     *
     * @param gameData the current game data (never null)
     * @param world the game world from which entities should be removed (never null)
     */
    void stop(GameData gameData, World world);
}
