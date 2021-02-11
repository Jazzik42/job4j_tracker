package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public final class Tracker {
    private static Tracker tracker = null;
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    private Tracker() {
    }

    public static Tracker getTracker() {
        if (tracker == null) {
            tracker = new Tracker();
        }
        return tracker;
    }

    public void add(Item item) {
        items.add(item);
        item.setId(ids++);
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String name) {
        List<Item> massiv = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                massiv.add(item);
            }
        }
        return massiv;
    }

    public Item findById(int id) {
        int index = indexOfI(id);
        return index != -1 ? items.get(index) : null;
    }

    private int indexOfI(int id) {
        int i = -1;
        for (Item item : items) {
            if (item.getId() == id) {
                i = items.indexOf(item);
                break;
            }
        }
        return i;
    }

    public boolean replace(int id, Item item) {
        int index = indexOfI(id);
        if (index != -1) {
            items.set(index, item);
            item.setId(id);
        }
        return index != -1;
    }

    public boolean delete(int id) {
        int index = indexOfI(id);
        if (index != -1) {
           items.remove(index);
        }
        return index != -1;
    }
}




