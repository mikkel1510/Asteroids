package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CollisionDetectorTest {

    private CollisionDetector detector;
    private Entity entity1, entity2;

    @BeforeEach
    void setup(){
        detector = spy(CollisionDetector.class);
        entity1 = mock(Entity.class);
        entity2 = mock(Entity.class);
    }

    @Test
    void collidesShouldReturnTrueWhenEntitiesOverlap() {
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getRadius()).thenReturn(10f);

        when(entity2.getX()).thenReturn(5.0);  // Within 10 + 10 radius = 20
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getRadius()).thenReturn(10f);

        boolean result = detector.collides(entity1, entity2);

        assertTrue(result);
    }

    @Test
    void collidesShouldReturnFalseWhenEntitiesDoNotOverlap() {
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getRadius()).thenReturn(10f);

        when(entity2.getX()).thenReturn(30.0);  // Distance > 20
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getRadius()).thenReturn(10f);

        boolean result = detector.collides(entity1, entity2);

        assertFalse(result);
    }

}
