package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public final class MemTracker {
    private static MemTracker memTracker = null;
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public static MemTracker getTracker() {
        if (memTracker == null) {
            memTracker = new MemTracker();
        }
        return memTracker;
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
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
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




