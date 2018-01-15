import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void calcTest() {
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 17;
        int result = 27;

        assertEquals(result, calculator.sum(a, b));
    }
    @Test
    public void calcTest2() {
        Calculator calculator2 = new Calculator();
        int c = 50;
        int d = 5;
        int result = 11;

        assertEquals(result, calculator2.div(c,d));
    }

}
