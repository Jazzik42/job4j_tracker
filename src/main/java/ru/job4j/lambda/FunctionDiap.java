package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionDiap {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int j = start; j < end; j++) {
            list.add(func.apply((double) j));
        }
        return list;
    }
}
