package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        final SessionFactory sF = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
        Session session = null;
        List<CarBrand> list = new ArrayList<>();
        try {
            session = sF.openSession();
            session.beginTransaction();
            list = session.createQuery(
                    "select distinct c from CarBrand c join fetch c.cars").list();
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (CarBrand cars : list) {
            for (CarModel car : cars.getCars()) {
                System.out.println(car);
            }
        }
    }
}
