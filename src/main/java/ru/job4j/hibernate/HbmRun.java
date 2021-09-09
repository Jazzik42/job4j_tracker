package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        final SessionFactory sF = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
        Session session = null;
        try {
            session = sF.openSession();
            session.beginTransaction();
            CarBrand volvo = new CarBrand("Volvo");
            volvo.addCars(new CarModel("xc40"));
            volvo.addCars(new CarModel("xc50"));
            volvo.addCars(new CarModel("xc60"));
            volvo.addCars(new CarModel("xc70"));
            session.save(volvo);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
