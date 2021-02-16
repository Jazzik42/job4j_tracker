package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNull.nullValue;

public class StartUITest {
    @Test

    public void testInit() {
        Tracker tracker = Tracker.getTracker();
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitProgramAction(output));
        String[] answer = new String[]{"0", "Item name", "1"};
        Input input = new StubInput(answer);
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceAction() {
        Tracker tracker = Tracker.getTracker();
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new ExitProgramAction(output));
        Item item = new Item("Item Name");
        tracker.add(item);
        String[] answers = new String[]{"0", "New Item Name", "1", "1"};
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is("New Item Name"));
    }

    @Test
    public void whenDeleteAction() {
        Tracker tracker = Tracker.getTracker();
        Output output = new ConsoleOutput();
        Item item = new Item("Item name");
        tracker.add(item);
        String[] answers = new String[]{"0", "1", "1"};
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitProgramAction(output));
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));

    }

    @Test
    public void whenOutput() {
        Tracker tracker = Tracker.getTracker();
        Output output = new StubOutput();
        String[] answers = new String[]{"0"};
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitProgramAction(output));
       Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator()
                + "0.===Exit===" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindAllAction() {
        Tracker tracker = Tracker.getTracker();
        Item item = new Item("Item name");
        tracker.add(item);
        String[] answers = new String[]{"0", "1"};
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindAllAction(output));
        actions.add(new ExitProgramAction(output));
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator()
                + "0.=== All item ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "=== All item ===" + System.lineSeparator()
                + item.getName() + " " + item.getId() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0.=== All item ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByNameAction() {
        Tracker tracker = Tracker.getTracker();
        Item item = new Item("Item name");
        String[] answers = new String[]{"0", "Item name", "1"};
        Input input = new StubInput(answers);
        tracker.add(item);
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(output));
        actions.add(new ExitProgramAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator()
                + "0.=== Find item by Name ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "=== Find item by Name ===" + System.lineSeparator()
                + "Item Id: " + item.getId() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0.=== Find item by Name ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByIdAction() {
        Tracker tracker = Tracker.getTracker();
        Item item = new Item("Item name");
        String[] answers = new String[]{"0", "1", "1"};
        Input input = new StubInput(answers);
        tracker.add(item);
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(output));
        actions.add(new ExitProgramAction(output));

        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator()
                + "0.=== Find item by Id ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "=== Find item by Id ===" + System.lineSeparator()
                + "Item Name: " + item.getName() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0.=== Find item by Id ===" + System.lineSeparator()
                + "1.===Exit===" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Tracker tracker = Tracker.getTracker();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"15", "0"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitProgramAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(String.format("Menu:%n"
                        + "0.===Exit===%n"
                        + "Wrong input, you can select: 0 .. 0%n"
                        + "Menu:%n"
                        + "0.===Exit===%n"
                        + "===Exit===%n"
                    )
        ));
}

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateConsoleInput input = new ValidateConsoleInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidateRight() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"1"});
        ValidateConsoleInput input = new ValidateConsoleInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidateAllRight() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"1", "1", "1"});
        ValidateConsoleInput input = new ValidateConsoleInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidateNegativeNumber() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"-1"});
        ValidateConsoleInput input = new ValidateConsoleInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-1));
    }
}





















