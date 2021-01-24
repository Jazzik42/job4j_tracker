package ru.job4j.tracker;

public class ExitProgramAction implements UserAction {
    public final Output output;

    public ExitProgramAction(Output output) {
        this.output = output;
    }
    @Override
    public String name() {
        return "===Exit===";
    }
    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("===Exit===");
        return false;
    }

}
