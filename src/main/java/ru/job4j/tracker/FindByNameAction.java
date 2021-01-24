package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    public final Output output;

    public FindByNameAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find item by Name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String itemName = input.askStr("Enter name:");
        Item[] itemArr = tracker.findByName(itemName);
        if (itemArr.length != 0) {
            for (Item items : itemArr) {
                output.println("Item Id:" + items.getId());
            }
        } else {
            output.println("There are no applications with this name.");
        }
        return true;
    }
}
