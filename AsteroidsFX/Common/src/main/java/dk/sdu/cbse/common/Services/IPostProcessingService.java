package dk.sdu.cbse.common.Services;


import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

/**
 * Service provider interface for post-processing of entities after the main entity updating
 * <p>
 *     Implementations typically perform operations such as collision detecting
 * </p>
 */
public interface IPostProcessingService {

    /**
     * Performs post-processing logic on the entities, such as handling collisions
     *
     * <p><b>Pre-condition:</b> All entities have been updated for the current game tick; the game data and world are valid</p>
     * <p><b>Post-condition:</b> Post-processing tasks, such as collision, have been handled</p>
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
