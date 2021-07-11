package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;

public class CompareTest {
    @Test
    public void nameCompareReverse() {
        NameCompareReverse nr = new NameCompareReverse();
        MemTracker memTracker = MemTracker.getTracker();
        memTracker.findAll().clear();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Collections.sort(memTracker.findAll(), nr);
        List<Item> items = new ArrayList(Arrays.asList(item2, item1, item3));
        assertThat(memTracker.findAll(), is(items));
    }

    @Test
    public void idCompareReverse() {
        IdCompareReverse ir = new IdCompareReverse();
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Deda");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Collections.sort(memTracker.findAll(), ir);
        List<Item> items = new ArrayList(Arrays.asList(item3, item2, item1));
        assertThat(memTracker.findAll(), is(items));
    }

    @Test
    public void nameCompare() {
        NameCompare nr = new NameCompare();
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Collections.sort(memTracker.findAll(), nr);
        List<Item> items = new ArrayList(Arrays.asList(item3, item1, item2));
        assertThat(memTracker.findAll(), is(items));
    }

    @Test
    public void idCompare() {
        IdCompare ir = new IdCompare();
        MemTracker memTracker = new MemTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Collections.sort(memTracker.findAll(), ir);
        List<Item> items = new ArrayList(Arrays.asList(item1, item2, item3));
        assertThat(memTracker.findAll(), is(items));
    }
}