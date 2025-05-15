package main.common.Services;

import main.common.Data.GameData;
import main.common.Data.World;

public interface IPostProcessor {
    void process(GameData gameData, World world);
}
