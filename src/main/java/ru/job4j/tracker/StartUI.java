package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println("Select:");
            int select = Integer.valueOf(scanner.nextLine());
            Item item;
            String itemName;
            int itemId;
            switch (select) {
                case 0:
                    System.out.println("=== Create a new Item ====");
                    System.out.println("Enter name:");
                    itemName = scanner.nextLine();
                    item = new Item();
                    tracker.add(item);
                    item.setName(itemName);
                    break;
                case 1:
                    System.out.println("=== All items ===");
                    for (Item items : tracker.findAll()) {
                        System.out.println(items.getName());
                    }
                    break;
                case 2:
                    System.out.println("=== Edit item ===");
                    item = new Item();
                    System.out.println("Enter name:");
                    itemName = scanner.nextLine();
                    System.out.println("Enter id:");
                    itemId = Integer.valueOf(scanner.nextLine());
                    item.setName(itemName);
                    System.out.println(tracker.replace(itemId, item) ? "The application has been replaced."
                            : "There is no application with this id.");
                    break;
                case 3:
                    System.out.println("=== Delete item ===");
                    System.out.println("Enter Id:");
                    itemId = Integer.valueOf(scanner.nextLine());
                    if (tracker.delete(itemId)) {
                        System.out.println("The application has been deleted.");
                    } else {
                        System.out.println("There is no application with this id.");
                    }
                    break;
                    case 4:
                    System.out.println("=== Find item by Id ===");
                    System.out.println("Enter Id:");
                    itemId = Integer.valueOf(scanner.nextLine());
                    item = tracker.findById(itemId);
                    if (item != null) {
                        System.out.println("Item Name:" + item.getName());
                    } else {
                        System.out.println("There is no application with this id.");
                    }
                    break;
                case 5:
                    System.out.println("=== Find item by Name ===");
                    System.out.println("Enter name:");
                    itemName = scanner.nextLine();
                    Item[] item2 = tracker.findByName(itemName);
                    if (item2 != null) {
                        for (Item items : item2) {
                            System.out.println("Item Id:" + items.getId());
                        }
                    } else {
                            System.out.println("There are no applications with this name.");
                    }
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);

            /*Item item = new Item();
            Item item2 = new Item();
            Item item3 = new Item();
            Item item4 = new Item();

            tracker.add(item);
            tracker.add(item2);
            tracker.add(item3);
            tracker.add(item4);
            item.setName("Ivan");
            item2.setName("OtherName");
            item3.setName("Ivan");
            item4.setName("OtherName2");
            System.out.println("All items:");
            for (Item items : tracker.findAll()) {
                System.out.println(items.getName());
            }
            System.out.println();
            System.out.println("Array with name - \"Ivan\":");
            for (Item mass : tracker.findByName("Ivan")) {
                System.out.println(mass.getName());
            }
            System.out.println();
            tracker.findById(item4.getId());
            System.out.println("Name itemId:");
            System.out.println(tracker.findById(3).getName());
            System.out.println();
            System.out.println("toString():");
            System.out.println(item);
        }
    }*/
    }
}

