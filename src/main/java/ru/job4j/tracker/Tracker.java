package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int size = 0;
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] massiv = Arrays.copyOf(items, size);
        return massiv;
    }

    public Item[] findByName(String name) {
        Item[] massiv = new Item[size];
        int buff = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(name)) {
                massiv[buff++] = items[i];
            }
        }
        massiv = Arrays.copyOf(massiv, buff);
        return massiv;
    }

    public Item findById(int id) {
        Item buff = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId() == id) {
                buff = items[i];
                break;
            }
        }
        return buff;
    }
}

