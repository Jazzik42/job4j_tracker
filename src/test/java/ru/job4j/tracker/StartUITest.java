package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNull.nullValue;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertThat(created.getName(), is(expected.getName()));

    }

    @Test
    public void replace() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        String[] answers = {"replaced item",
                String.valueOf(item.getId())

        };
        StartUI.editItem(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void delete() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        String[] answer = {String.valueOf(item.getId())};
        StartUI.deleteItem(new StubInput(answer), tracker);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void testInit() {
        Tracker tracker = new Tracker();
        Output output = new ConsoleOutput();
        UserAction[] userAction = {new CreateAction(output), new ExitProgramAction(output)};
        String[] answer = new String[]{"0", "Item name", "1"};
        Input input = new StubInput(answer);
        new StartUI(output).init(input, tracker, userAction);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceAction() {
        Tracker tracker = new Tracker();
        Output output = new ConsoleOutput();
        UserAction[] userAction = {new ReplaceAction(output), new ExitProgramAction(output)};
        Item item = new Item("Item Name");
        tracker.add(item);
        String[] answers = new String[]{"0", "New Item Name", "1", "1"};
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, userAction);
        assertThat(tracker.findById(item.getId()).getName(), is("New Item Name"));
    }

    @Test
    public void whenDeleteAction() {
        Tracker tracker = new Tracker();
        Output output = new ConsoleOutput();
        Item item = new Item("Item name");
        tracker.add(item);
        String[] answers = new String[]{"0", "1", "1"};
        UserAction[] userAction = {new DeleteAction(output), new ExitProgramAction(output)};
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, userAction);
        assertThat(tracker.findById(item.getId()), is(nullValue()));

    }

    @Test
    public void whenOutput() {
        Tracker tracker = new Tracker();
        Output output = new StubOutput();
        String[] answers = new String[]{"0"};
        UserAction[] userActions = {new ExitProgramAction(output)};
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, userActions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator() + "===Exit===" + System.lineSeparator()));
    }

    @Test
    public void whenFindAllAction() {
        Tracker tracker = new Tracker();
        Item item = new Item("Item name");
        tracker.add(item);
        String[] answers = new String[]{"0", "1"};
        Output output = new StubOutput();
        UserAction[] userActions = {new FindAllAction(output), new ExitProgramAction(output)};
        Input input = new StubInput(answers);
        new StartUI(output).init(input, tracker, userActions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator() +
                "=== All item ===" + System.lineSeparator() +
                item.getName() + " " + item.getId() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByNameAction() {
        Tracker tracker = new Tracker();
        Item item = new Item("Item name");
        String[] answers = new String[]{"0", "Item name", "1"};
        Input input = new StubInput(answers);
        tracker.add(item);
        Output output = new StubOutput();
        UserAction[] userActions = {new FindByNameAction(output), new ExitProgramAction(output)};
        new StartUI(output).init(input, tracker, userActions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator() +
                "=== Find item by Name ===" + System.lineSeparator() +
                "Item Id: " + item.getId() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByIdAction() {
        Tracker tracker = new Tracker();
        Item item = new Item("Item name");
        String[] answers = new String[]{"0", "1", "1"};
        Input input = new StubInput(answers);
        tracker.add(item);
        Output output = new StubOutput();
        UserAction[] userActions = {new FindByIdAction(output), new ExitProgramAction(output)};
        new StartUI(output).init(input, tracker, userActions);
        assertThat(output.toString(), is("Menu:" + System.lineSeparator() +
                "=== Find item by Id ===" + System.lineSeparator() +
                "Item Name: " + item.getName() + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "===Exit===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"15", "0"});
        UserAction[] userActions = {new ExitProgramAction(out)};
        new StartUI(out).init(in, tracker, userActions);
        assertThat(out.toString(), is(  String.format(       "Menu:%n"
                        + "0.===Exit===%n"
                        + "Wrong input, you can select: 0 .. 0%n"
                        + "Menu:%n"
                        + "0.===Exit===%n"
                        + "===Exit===%n"
                    )
        ));
}
}





















