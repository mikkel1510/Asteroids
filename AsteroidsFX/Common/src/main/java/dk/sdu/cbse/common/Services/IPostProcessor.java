package dk.sdu.cbse.common.Services;


import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

public interface IPostProcessor {
    void process(GameData gameData, World world);
}
