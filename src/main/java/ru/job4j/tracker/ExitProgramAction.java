package ru.job4j.tracker;

public class ExitProgramAction implements UserAction {
    private final Output output;

    public ExitProgramAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "===Exit===";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("===Exit===");
        return false;
    }

}
