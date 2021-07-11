package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private final Output output;

    public FindByIdAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        output.println("=== Find item by Id ===");
        int itemId = input.askInt("Enter Id:");
        Item item = memTracker.findById(itemId);
        if (item != null) {
            output.println("Item Name: " + item.getName());
        } else {
            output.println("There is no application with this id.");
        }
        return true;
    }
}
