package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionDiap {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
    List<Double> list = new ArrayList<>();
        for (int i = start; i < end ; i++) {
            double a = i;
            list.add(func.apply(a));
        }
    return list;
    }
}
