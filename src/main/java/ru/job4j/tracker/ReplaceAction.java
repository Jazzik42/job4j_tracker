package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item item = new Item();
        String itemName = input.askStr("Enter name:");
        int itemId = input.askInt("Enter id:");
        item.setName(itemName);
        System.out.println(tracker.replace(itemId, item) ? "The application has been replaced."
                : "There is no application with this id.");
    return true;
    }
}


