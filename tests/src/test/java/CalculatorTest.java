import org.hamcrest.MatcherAssert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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

    @Test
    public void calcTest3() {
        Calculator calculator3 = new Calculator();
        int e = 81;
        int result = 10;

        assertThat(calculator3.sqrt(e)).as("Извлечение корня работает неправильно!").isEqualTo(result);
    }

    @Test
    public void calcTest4() {
        Calculator calculator4 = new Calculator();
        int a = 2;
        int b = 3;
        double result = 8;

        MatcherAssert.assertThat("Возведение в степень работает неправильно!", calculator4.pow(a, b), equalTo(result));
    }

}
