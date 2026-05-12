package com.spacetravel.dao.impl;

import com.spacetravel.dao.PlanetDao;
import com.spacetravel.model.Planet;
import com.spacetravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetDaoImpl implements PlanetDao {

    @Override
    public void save(Planet planet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.persist(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Planet findById(long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.find(Planet.class, id);
        }
    }

    @Override
    public void update(Planet planet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.merge(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Planet planet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            tx = session.beginTransaction();
            session.remove(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Planet> findAll() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }
}