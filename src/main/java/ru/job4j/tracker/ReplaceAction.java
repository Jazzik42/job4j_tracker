package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output output;

    public ReplaceAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        String itemName = input.askStr("Enter name:");
        int itemId = input.askInt("Enter id:");
        Item item = new Item(itemName, itemId);
        output.println(memTracker.replace(itemId, item) ? "The application has been replaced."
                : "There is no application with this id.");
        return true;
    }
}


