package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class SearchAtt {

    public static List<Attachment> filter(List<Attachment> list, Predicate<Attachment> predicate) {
        List<Attachment> newList = new ArrayList<>();
        for (Attachment att : list) {
            if (predicate.test(att)) {
                newList.add(att);
            }
        }
        return newList;
    }

    public static List<Attachment> filterName(List<Attachment> list, String name) {
        Predicate<Attachment> predicate = att -> att.getName().contains(name);
        return filter(list, predicate);
    }

    public static List<Attachment> filterSize(List<Attachment> list, int size) {
        Predicate<Attachment> predicate = att -> att.getSize() > size;
        return filter(list, predicate);
    }

    public static void main(String[] args) {
        List<Attachment> list = List.of(
                new Attachment("qwerty", 10),
                new Attachment("qwerBug", 150),
                new Attachment("qwer", 120)
        );
        System.out.println(filterSize(list, 100));
        System.out.println(filterName(list, "Bug"));
    }
}

