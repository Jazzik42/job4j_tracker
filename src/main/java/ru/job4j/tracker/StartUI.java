package ru.job4j.tracker;



public class StartUI {
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            switch (select) {
                case 0:
                    System.out.println("=== Create a new Item ====");
                    String itemName1 = input.askStr("Enter name:");
                    Item item1 = new Item(itemName1);
                    tracker.add(item1);
                    break;
                case 1:
                    System.out.println("=== All items ===");
                    for (Item items : tracker.findAll()) {
                        System.out.println(items.getName() + " " + items.getId());
                    }
                    break;
                case 2:
                    System.out.println("=== Edit item ===");
                    Item item2 = new Item();
                    String itemName2 = input.askStr("Enter name:");
                    int itemId2 = input.askInt("Enter id:");
                    item2.setName(itemName2);
                    System.out.println(tracker.replace(itemId2, item2) ? "The application has been replaced."
                            : "There is no application with this id.");
                    break;
                case 3:
                    System.out.println("=== Delete item ===");
                    int itemId3 = input.askInt("Enter Id:");
                    if (tracker.delete(itemId3)) {
                        System.out.println("The application has been deleted.");
                    } else {
                        System.out.println("There is no application with this id.");
                    }
                    break;
                    case 4:
                    System.out.println("=== Find item by Id ===");
                    int itemId4 = input.askInt("Enter Id:");
                    Item item4 = tracker.findById(itemId4);
                    if (item4 != null) {
                        System.out.println("Item Name:" + item4.getName());
                    } else {
                        System.out.println("There is no application with this id.");
                    }
                    break;
                case 5:
                    System.out.println("=== Find item by Name ===");
                    String itemName5 = input.askStr("Enter name:");
                    Item[] itemArr = tracker.findByName(itemName5);
                    if (itemArr != null) {
                        for (Item items : itemArr) {
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
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);

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

