package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output output;

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Delete Item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int item = input.askInt("Enter Id:");
        if (tracker.delete(item)) {
            output.println("The application has been deleted.");
        } else {
            output.println("There is no application with this id.");
        }

        return true;
    }

}

