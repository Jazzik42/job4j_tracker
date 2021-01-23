package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int itemId = input.askInt("Enter Id:");
        Item item = tracker.findById(itemId);
        if (item != null) {
            System.out.println("Item Name:" + item.getName());
        } else {
            System.out.println("There is no application with this id.");
        }
        return true;
    }
}
