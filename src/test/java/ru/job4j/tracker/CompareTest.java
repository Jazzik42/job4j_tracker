package ru.job4j.tracker;

import junit.framework.TestCase;
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
        Tracker tracker = Tracker.getTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Collections.sort(tracker.findAll(), nr);
        List<Item> items = new ArrayList(Arrays.asList(item2, item1, item3));
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void idCompareReverse() {
        IdCompareReverse ir = new IdCompareReverse();
        Tracker tracker = Tracker.getTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Collections.sort(tracker.findAll(), ir);
        List<Item> items = new ArrayList(Arrays.asList(item3, item2, item1));
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void nameCompare() {
        NameCompare nr = new NameCompare();
        Tracker tracker = Tracker.getTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Collections.sort(tracker.findAll(), nr);
        List<Item> items = new ArrayList(Arrays.asList(item3, item1, item2));
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void idCompare() {
        IdCompare ir = new IdCompare();
        Tracker tracker = Tracker.getTracker();
        Item item1 = new Item("Mama");
        Item item2 = new Item("Papa");
        Item item3 = new Item("Ded");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Collections.sort(tracker.findAll(), ir);
        List<Item> items = new ArrayList(Arrays.asList(item1, item2, item3));
        assertThat(tracker.findAll(), is(items));
    }
}