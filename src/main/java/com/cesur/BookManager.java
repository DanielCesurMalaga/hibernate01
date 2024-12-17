package com.cesur;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class BookManager {
    protected SessionFactory sessionFactory;

    protected void setup() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            // sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.out.println(e.getMessage());
        }
    }

    protected void exit() {
        if ((sessionFactory != null) && (!sessionFactory.isClosed()))
            sessionFactory.close();
    }

    protected void create(String title, String author, Float price) {
        Book book = new Book();
        book.setAuthor(author);
        book.setPrice(price);
        book.setTitle(title);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();

        session.close();

    }

    protected void read(int numLibro) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, numLibro);
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());
        session.close();
    }

    protected void update() {
        Book book = new Book();
        book.setId(3);
        book.setTitle("Java e Hibernate");
        book.setAuthor("Noelia Sanchez");
        book.setPrice(19.99f);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
        session.close();
    }

    protected void delete(int id) {
        Book book = new Book();
        book.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }

    protected void readAll(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Book> consulta = session.createQuery("FROM Book",Book.class);
        List<Book> listadoLibros = consulta.list();
        for (int i = 0; i < listadoLibros.size(); i++) {
            System.out.println(listadoLibros.get(i).toString());
        }
        

        session.close();
    }
}
