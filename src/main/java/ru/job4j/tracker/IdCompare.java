package ru.job4j.tracker;

import java.util.Comparator;

public class IdCompare implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        Integer one = first.getId();
        Integer two = second.getId();
        return one.compareTo(two);
    }
}
