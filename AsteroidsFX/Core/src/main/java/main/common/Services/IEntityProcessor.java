package main.common.Services;

import main.common.Data.GameData;
import main.common.Data.World;

public interface IEntityProcessor {
    void process(GameData gameData, World world);
}
