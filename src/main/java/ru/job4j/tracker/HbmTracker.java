package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    private final SessionFactory sF = new MetadataSources(registry).buildMetadata()
            .buildSessionFactory();

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        Session session = sF.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        Session session = sF.openSession();
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("such entity does not exist", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        Session session = sF.openSession();
        try {
            session.beginTransaction();
            Item item = new Item(null);
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("such entity does not exist", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sF.openSession();
        List<Item> list = null;
        try {
            session.beginTransaction();
            list = session.createQuery("from ru.job4j.tracker.Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("data base is empty", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sF.openSession();
        List<Item> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ru.job4j.tracker.Item where id = :key");
            query.setParameter("key", key);
            list = query.list();
        } catch (Exception e) {
            LOGGER.error("such element's not found", e);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        Session session = sF.openSession();
        Item item = null;
        try {
            session.beginTransaction();
            item = session.get(Item.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("such element not found", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}

