package dk.sdu.cbse.common.Services;

import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
