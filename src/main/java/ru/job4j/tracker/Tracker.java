package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int size = 0;
    private int ids = 1;

    public void add(Item item) {
        items[size++] = item;
        item.setId(ids++);
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String name) {
        Item[] massiv = new Item[size];
        int buff = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(name)) {
                massiv[buff++] = items[i];
            }
        }
        return Arrays.copyOf(massiv, buff);
    }

    public Item findById(int id) {
         int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }
    private int indexOf(int id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (items[i].getId() == id) {
                index = i;
            }
        }
        return index;
    }
        public boolean replace(int id, Item item) {
         items[indexOf(id)] = item;
            item.setId(id);
            return indexOf(id) != -1;

    }
}



