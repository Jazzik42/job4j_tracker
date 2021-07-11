//package ru.job4j.tracker;
//
//import org.junit.Test;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.nullValue;
//import static org.junit.Assert.assertThat;
//
//public class MemTrackerTest {
//    @Test
//    public void whenAddNewItemThenTrackerHasSameItem() {
//        Store tracker = new Store();
//        Item item = new Item();
//        item.setName("test1");
//        Store.add(item);
//        Item result = Store.findById(item.getId());
//        assertThat(result.getName(), is(item.getName()));
//    }
//
//    @Test
//    public void whenReplace() {
//        Store tracker = new Store();
//        Item bug = new Item("Bug");
//        Store.add(bug);
//        int id = bug.getId();
//        Item bugWithDesc = new Item("Bug with description");
//        Store.replace(id, bugWithDesc);
//        assertThat(Store.findById(id).getName(), is("Bug with description"));
//    }
//
//    @Test
//    public void whenDelete() {
//        Store Store = new Store();
//        Item item = new Item();
//        item.setName("Item delete");
//        Store.add(item);
//        int id = item.getId();
//        Store.delete(id);
//        assertThat(Store.findById(id), is(nullValue()));
//    }
//
//}