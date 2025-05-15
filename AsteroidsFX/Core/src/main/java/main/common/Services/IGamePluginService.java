package main.common.Services;

import main.common.Data.GameData;
import main.common.Data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
