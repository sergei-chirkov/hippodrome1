import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    void whenConstructurNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Horse(null, 20.0, 12.0);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "    "})
    void whenNameIsEmpty(String str) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(str, 20.0, 12.0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenSpeedIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", -1.0, 12.0);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenDistanseIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", 12, -1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }

    Horse horse = new Horse("horse", 12, 20);

    @Test
    void CheckgetName() {
        assertEquals("horse", horse.getName());
    }

    @Test
    void CheckGetSpeed() {
        assertEquals(12, horse.getSpeed());
    }

    @Test
    void CheckGetDistance() {
        assertEquals(20, horse.getDistance());
    }

    @Test
    void checkCallMethodGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            double randomDouble = Horse.getRandomDouble(0.2, 0.9);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void checkWorkMethodMove() {
        try(MockedStatic mockedStatic = Mockito.mockStatic(Horse.class)){
            Mockito.when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(1.0);
            short result = (short) (horse.getDistance() * horse.getSpeed() * 1.0);
            assertEquals(result,horse.move());
        }


    }

}
