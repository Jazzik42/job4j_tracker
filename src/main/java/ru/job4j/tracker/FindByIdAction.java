package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    public final Output output;

    public FindByIdAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Find item by Id ===");
        int itemId = input.askInt("Enter Id:");
        Item item = tracker.findById(itemId);
        if (item != null) {
            output.println("Item Name: " + item.getName());
        } else {
            output.println("There is no application with this id.");
        }
        return true;
    }
}
