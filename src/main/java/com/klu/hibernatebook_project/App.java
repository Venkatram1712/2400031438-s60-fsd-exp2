package com.klu.hibernatebook_project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        // Open Session
        Session session = factory.openSession();

        // Begin Transaction
        Transaction tx = session.beginTransaction();

        // --------------------
        // 1. INSERT
        // --------------------
        Book b1 = new Book("Java Programming", "James Gosling", 550.00);
        session.persist(b1);

        // --------------------
        // 2. READ
        // --------------------
        Book book = session.find(Book.class, b1.getId());
        if (book != null) {
            System.out.println("Book Title: " + book.getTitle());
        }

        // --------------------
        // 3. UPDATE
        // --------------------
        if (book != null) {
            book.setPrice(500.00);
            session.merge(book);
        }

        // --------------------
        // 4. DELETE (optional)
        // --------------------
        // session.remove(book);

        // Commit Transaction
        tx.commit();

        // Close Session & Factory
        session.close();
        factory.close();
    }
}
