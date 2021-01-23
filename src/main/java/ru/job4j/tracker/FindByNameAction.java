package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by Name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println();
        String itemName = input.askStr("Enter name:");
        Item[] itemArr = tracker.findByName(itemName);
        if (itemArr.length != 0) {
            for (Item items : itemArr) {
                System.out.println("Item Id:" + items.getId());
            }
        } else {
            System.out.println("There are no applications with this name.");
        }
        return true;
    }
}
