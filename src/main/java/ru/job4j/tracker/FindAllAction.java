package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    private final Output output;

    public FindAllAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== All item ===";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        output.println("=== All item ===");
        for (Item items : memTracker.findAll()) {
            output.println(items.getName() + " " + items.getId());
        }
        return true;
    }
}

