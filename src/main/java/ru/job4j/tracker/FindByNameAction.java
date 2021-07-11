package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    private final Output output;

    public FindByNameAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find item by Name ===";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Find item by Name ===");
        String itemName = input.askStr("Enter name:");
        List<Item> itemArr = tracker.findByName(itemName);
        if (itemArr.size() != 0) {
            for (Item items : itemArr) {
                output.println("Item Id: " + items.getId());
            }
        } else {
            output.println("There are no applications with this name.");
        }
        return true;
    }
}
