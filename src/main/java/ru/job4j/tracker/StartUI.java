package ru.job4j.tracker;

import java.sql.SQLOutput;

public class StartUI {

    public static void main(String[] args) {
        Item item = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        Item item4 = new Item();
        Tracker tracker = new Tracker();
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        item.setName("Ivan");
        item2.setName("OtherName");
        item3.setName("Ivan");
        item4.setName("OtherName2");
        System.out.println("All items:");
        for (Item items: tracker.findAll()) {
            System.out.println(items.getName());
        }
        System.out.println();
        System.out.println("Array with name - \"Ivan\":");
        for (Item mass: tracker.findByName("Ivan")) {
            System.out.println(mass.getName());
         }
        System.out.println();
        tracker.findById(item4.getId());
        System.out.println("Name itemId:");
        System.out.println(tracker.findById(3).getName());
    }
}
