import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TestHippodrome {

    @Test
    void whenConstructorIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void checkConstructorWhenListHorseIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    List<Horse> horseList = new ArrayList<>();

    @BeforeEach
    void init() {
        horseList.add(new Horse("a", 1, 7));
        horseList.add(new Horse("b", 2, 8));
        horseList.add(new Horse("c", 3, 9));
        horseList.add(new Horse("d", 4, 10));
        horseList.add(new Horse("e", 5, 11));
        horseList.add(new Horse("f", 6, 12));
    }

    @Test
    void CheckMethodGetHorses() {

        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @ExtendWith(MockitoExtension.class)
    @Mock
    Horse horse;

    @Test
    void CheckMethodMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, times(50)).move();
    }

    @Test
    void MethodGetWinnerMustGetMaxDistance() {
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(hippodrome.getWinner().getDistance(), 12);
    }

}
