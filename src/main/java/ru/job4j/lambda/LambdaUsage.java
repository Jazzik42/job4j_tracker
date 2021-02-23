package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaUsage {

    public static void main(String[] args) {

        Comparator<String> cmpText = (left, right) -> {
            System.out.println("compare - " + left + " : " + right);
            return left.compareTo(right);
        };

        Comparator<String> cmpDescSize = (left, right) -> {
            System.out.println("compare - " + left + " : " + right);
            return Integer.compare(right.length(), left.length());
        };

        System.out.println(cmpText.compare("Petr", "Artem"));
        System.out.println(cmpDescSize.compare("Petr", "Artem"));
    }
    }