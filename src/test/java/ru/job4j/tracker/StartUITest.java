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
    public void replace(){
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        String[] answers = { "replaced item",
                String.valueOf(item.getId())

        };
        StartUI.editItem(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void delete(){
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
        UserAction[] userAction = {new CreateAction(), new ExitProgramAction()};
        String[] answer = new String[] {"0", "Item name", "1"};
        Input input = new StubInput(answer);
        new StartUI().init(input, tracker, userAction);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceAction() {
        Tracker tracker = new Tracker();
        UserAction[] userAction = {new ReplaceAction(), new ExitProgramAction()};
        Item item = new Item("Item Name");
        tracker.add(item);
        String[] answers = new String[] {"0", "New Item Name", "1", "1"};
        Input input = new StubInput(answers);
        new StartUI().init(input, tracker, userAction);
        assertThat(tracker.findById(item.getId()).getName(), is("New Item Name"));
    }

    @Test
    public void whenDeleteAction() {
        Tracker tracker = new Tracker();
        Item item = new Item("Item name");
        tracker.add(item);
        String[] answers = new String[] {"0", "1", "1"};
        UserAction[] userAction = {new DeleteAction(), new ExitProgramAction()};
        Input input = new StubInput(answers);
        new StartUI().init(input, tracker, userAction);
        assertThat(tracker.findById(item.getId()), is(nullValue()));

    }























}






















