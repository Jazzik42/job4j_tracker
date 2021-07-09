package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output output;

    @Override
    public String name() {
        return "=== Edit item ===";
    }

    public ReplaceAction(Output output) {
        this.output = output;
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String itemName = input.askStr("Enter name:");
        int itemId = input.askInt("Enter id:");
        Item item = new Item(itemName, itemId);
        output.println(tracker.replace(itemId, item) ? "The application has been replaced."
                : "There is no application with this id.");
        return true;
    }
}


