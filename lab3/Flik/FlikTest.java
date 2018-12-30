import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void testList() {
        int a = 3, b = 3;
        assertTrue(Flik.isSameNumber(a, b));

        int c = 0, d = 1;
        assertFalse(Flik.isSameNumber(c, d));

        int e = 128, f = 128;
        assertTrue(Flik.isSameNumber(e, f));
    }
}
