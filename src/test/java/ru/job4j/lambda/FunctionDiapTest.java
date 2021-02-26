package ru.job4j.lambda;


import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

public class FunctionDiapTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionDiap function = new FunctionDiap();
        List<Double> newList = List.of(11D, 13D, 15D);
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        assertThat(result, is(newList));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        FunctionDiap function = new FunctionDiap();
        List<Double> newList = List.of(25D, 36D, 49D);
        List<Double> result = function.diapason(5, 8, x -> x * x);
        assertThat(result, is(newList));
    }

    @Test
    public void testDiapason() {
        FunctionDiap function = new FunctionDiap();
        List<Double> newList = List.of(3125D, 15625D, 78125D);
        List<Double> result = function.diapason(5, 8, x -> Math.pow(5D, x));
        assertThat(result, is(newList));
    }
}