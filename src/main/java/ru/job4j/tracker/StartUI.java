package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    public final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                output.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
            }
        }

    private void showMenu(List<UserAction> actions) {
        output.println("Menu:");
        for (UserAction action : actions) {
            output.println(actions.indexOf(action) + "." + action.name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateConsoleInput(output, new ConsoleInput());
        Tracker tracker = Tracker.getTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new DeleteAction(output));
        actions.add(new FindAllAction(output));
        actions.add(new FindByIdAction(output));
        actions.add(new FindByNameAction(output));
        actions.add(new ReplaceAction(output));
        actions.add(new ExitProgramAction(output));
        new StartUI(output).init(input, tracker, actions);
    }














}

