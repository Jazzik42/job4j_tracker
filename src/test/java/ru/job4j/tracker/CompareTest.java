//package ru.job4j.tracker;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.hamcrest.Matchers.is;
//
//public class CompareTest {
//    @Test
//    public void nameCompareReverse() {
//        NameCompareReverse nr = new NameCompareReverse();
//        Store tracker = Store.getTracker();
//        Store.findAll().clear();
//        Item item1 = new Item("Mama");
//        Item item2 = new Item("Papa");
//        Item item3 = new Item("Ded");
//        Store.add(item1);
//        Store.add(item2);
//        Store.add(item3);
//        Collections.sort(Store.findAll(), nr);
//        List<Item> items = new ArrayList(Arrays.asList(item2, item1, item3));
//        assertThat(tracker.findAll(), is(items));
//    }
//
//    @Test
//    public void idCompareReverse() {
//        IdCompareReverse ir = new IdCompareReverse();
//        Store Store = new Store();
//        Item item1 = new Item("Mama");
//        Item item2 = new Item("Papa");
//        Item item3 = new Item("Deda");
//        Store.add(item1);
//        Store.add(item2);
//        Store.add(item3);
//        Collections.sort(Store.findAll(), ir);
//        List<Item> items = new ArrayList(Arrays.asList(item3, item2, item1));
//        assertThat(Store.findAll(), is(items));
//    }
//
//    @Test
//    public void nameCompare() {
//        NameCompare nr = new NameCompare();
//        Store Store = new Store();
//        Item item1 = new Item("Mama");
//        Item item2 = new Item("Papa");
//        Item item3 = new Item("Ded");
//        Store.add(item1);
//        Store.add(item2);
//        Store.add(item3);
//        Collections.sort(Store.findAll(), nr);
//        List<Item> items = new ArrayList(Arrays.asList(item3, item1, item2));
//        assertThat(Store.findAll(), is(items));
//    }
//
//    @Test
//    public void idCompare() {
//        IdCompare ir = new IdCompare();
//        Store Store = new Store();
//        Item item1 = new Item("Mama");
//        Item item2 = new Item("Papa");
//        Item item3 = new Item("Ded");
//        Store.add(item1);
//        Store.add(item2);
//        Store.add(item3);
//        Collections.sort(Store.findAll(), ir);
//        List<Item> items = new ArrayList(Arrays.asList(item1, item2, item3));
//        assertThat(Store.findAll(), is(items));
//    }
//}