package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CollisionDetectorTest {

    private CollisionDetector detector;
    private World world;
    private GameData gameData;
    private Entity entity1, entity2;

    @BeforeEach
    void setup(){
        detector = spy(CollisionDetector.class);
        world = new World();
        gameData = new GameData();
        entity1 = mock(Entity.class);
        entity2 = mock(Entity.class);
    }

    @Test
    void shouldCallCollidesWhenEntitiesOverlap(){
        when(entity1.getID()).thenReturn("1");
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{-5,-5,10,0,-5,5});
        world.addEntity(entity1);

        when(entity2.getID()).thenReturn("2");
        when(entity2.getX()).thenReturn(0.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{-5,-5,10,0,-5,5});
        world.addEntity(entity2);

        detector.process(gameData, world);

        verify(detector).collides(entity1, entity2);
    }

    @Test
    void shouldNotCallCollidesWhenEntitiesDoNotOverlap(){
        when(entity1.getID()).thenReturn("1");
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{-5,-5,10,0,-5,5});
        world.addEntity(entity1);

        when(entity2.getID()).thenReturn("2");
        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(100.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{-5,-5,10,0,-5,5});
        world.addEntity(entity2);

        detector.process(gameData, world);

        verify(detector).collides(entity1, entity2);
    }
}
