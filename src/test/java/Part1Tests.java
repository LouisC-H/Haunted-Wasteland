import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Part1Tests {

    @Test
    void testA(){
        Assertions.assertEquals(2, Main.runDay8Code("src/main/resources/test1.txt"));
    }

    @Test
    void testB(){
        Assertions.assertEquals(6, Main.runDay8Code("src/main/resources/test2.txt"));
    }

    @Test
    void testC(){
        Assertions.assertEquals(6, Main.runDay8Code("src/main/resources/test3.txt"));
    }
}
