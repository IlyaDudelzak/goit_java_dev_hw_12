package com.spacetravel.crud;

import com.spacetravel.model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService{
    public void save(Client client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        }
    }

    public Client findById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Client.class, id);
        }
    }

    public void update(Client client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        }
    }

    public void delete(Client client) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        }
    }
}