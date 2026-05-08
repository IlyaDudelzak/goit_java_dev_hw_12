package com.spacetravel.crud;

import com.spacetravel.model.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetCrudService{
    public void save(Planet client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        }
    }

    public Planet findById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Planet.class, id);
        }
    }

    public void update(Planet client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        }
    }

    public void delete(Planet client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        }
    }
}