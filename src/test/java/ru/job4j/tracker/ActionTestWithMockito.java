package ru.job4j.tracker;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ActionTestWithMockito {


    @Test
    public void whenReplaceFirstTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        assertThat(out.toString(), is("The application has been replaced.\r\n"));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenReplaceSecondTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        rep.execute(input, tracker);
        assertThat(out.toString(), is("There is no application with this id.\r\n"));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }

    @Test
    public void whenDeleteTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("ItemName"));
        Input in = mock(Input.class);
        DeleteAction del = new DeleteAction(out);
        when(in.askInt(any(String.class))).thenReturn(1);
        del.execute(in, tracker);
        assertThat(out.toString(), is("The application has been deleted.\r\n"));
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void whenFindByIdTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("ItemName"));
        Input in = mock(Input.class);
        FindByIdAction find = new FindByIdAction(out);
        when(in.askInt(any(String.class))).thenReturn(1);
        find.execute(in, tracker);
        assertThat(out.toString(), is("Item Name: ItemName\r\n"));
    }

    @Test
    public void whenFindByIdSecondTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("ItemName"));
        Input in = mock(Input.class);
        FindByIdAction find = new FindByIdAction(out);
        when(in.askInt(any(String.class))).thenReturn(2);
        find.execute(in, tracker);
        assertThat(out.toString(), is("There is no application with this id.\r\n"));
    }

    @Test
    public void whenFindByNameFirstTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("ItemName"));
        Input in = mock(Input.class);
        FindByNameAction find = new FindByNameAction(out);
        when(in.askStr(any(String.class))).thenReturn("ItemName");
        find.execute(in, tracker);
        assertThat(out.toString(), is("Item Id: 1\r\n"));
    }

    @Test
    public void whenFindByNameSecondTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("ItemName"));
        Input in = mock(Input.class);
        FindByNameAction find = new FindByNameAction(out);
        when(in.askStr(any(String.class))).thenReturn("ItemNameName");
        find.execute(in, tracker);
        assertThat(out.toString(), is("There are no applications with this name.\r\n"));
    }
}