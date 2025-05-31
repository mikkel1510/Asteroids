package dk.sdu.cbse.common.Services;

import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

/**
 * Service provider interface for processing and updating entities during the game loop
 * <p>
 *     Implementations of this interface encapsulate logic for updating entities' states, such as movement and shooting
 * </p>
 */
public interface IEntityProcessingService {
    /**
     * Processes and updates relevant entities in the world according to current game data
     * <p><b>Pre-condition:</b> The world contains the entities to be processed: game data is current and valid</p>
     * <p><b>Post-condition:</b> The states of relevant entities have been updated according to the implemented logic</p>
     * @param gameData the current game data (never null)
     * @param world the game world containing entities to process (never null)
     */
    void process(GameData gameData, World world);
}
