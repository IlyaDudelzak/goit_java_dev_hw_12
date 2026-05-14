package com.spacetravel.dao.impl;

import com.spacetravel.dao.TicketDao;
import com.spacetravel.model.Ticket;
import com.spacetravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    @Override
    public void save(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Ticket findById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Ticket.class, id);
        }
    }

    @Override
    public void update(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.remove(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }
}