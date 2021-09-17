package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        final SessionFactory sF = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
        List<CarBrand> list = null;
        try (Session session = sF.openSession()) {
            session.beginTransaction();
            CarBrand carBrand = new CarBrand("Volvo");
            CarModel carModel1 = new CarModel("xc40");
            CarModel carModel2 = new CarModel("xc50");
            CarModel carModel3 = new CarModel("xc60");
            CarModel carModel4 = new CarModel("xc70");
            carModel1.setCarBrand(carBrand);
            carModel2.setCarBrand(carBrand);
            carModel3.setCarBrand(carBrand);
            carModel4.setCarBrand(carBrand);
            session.save(carBrand);
            session.save(carModel1);
            session.save(carModel2);
            session.save(carModel3);
            session.save(carModel4);
            session.clear();
            list = session.createQuery(
                    "select distinct c from CarBrand c join fetch c.cars").list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (CarBrand carBrands : list) {
            for (CarModel carModels : carBrands.getCars()) {
                System.out.println(carModels);
            }
        }
    }
}
