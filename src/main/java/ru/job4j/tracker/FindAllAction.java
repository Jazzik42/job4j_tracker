package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    @Override
    public String name() {
        return "=== All item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item items : tracker.findAll()) {
            System.out.println(items.getName() + " " + items.getId());
        }
        return true;
    }
}

