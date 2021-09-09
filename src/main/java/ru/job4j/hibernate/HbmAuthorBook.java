package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmAuthorBook {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sF = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
        Session session = null;
        try {
            session = sF.openSession();
            session.beginTransaction();
            Author author1 = new Author();
            Author author2 = new Author();
            Author author3 = new Author();
            Book book1 = new Book();
            Book book2 = new Book();
            Book book3 = new Book();
            author1.addBook(book1);
            author1.addBook(book2);
            author2.addBook(book1);
            author3.addBook(book3);
            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.delete(session.get(Author.class, 1));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
