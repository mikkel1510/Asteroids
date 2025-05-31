package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IPostProcessingService;

public class DuplicateClass implements IPostProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        System.out.println("Duplicate class in Player module");
    }
}
