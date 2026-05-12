package com.spacetravel.dao.impl;

import com.spacetravel.dao.ClientDao;
import com.spacetravel.model.Client;
import com.spacetravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDaoImpl implements ClientDao {

    @Override
    public void save(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Client findById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Client.class, id);
        }
    }

    @Override
    public void update(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Client> findAll() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }
}