package ru.job4j.tracker;


import javax.security.auth.login.CredentialException;

public class StartUI {
    public final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.length) {
                output.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, tracker);
            }
        }

    private void showMenu(UserAction[] actions) {
        output.println("Menu:");
        for (int i = 0; i < actions.length; i++) {
            output.println(i + "." + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(output), new DeleteAction(output), new FindAllAction(output),
        new FindByIdAction(output), new FindByNameAction(output), new ReplaceAction(output), new ExitProgramAction(output)};
        new StartUI(output).init(input, tracker, actions);

    }














}

