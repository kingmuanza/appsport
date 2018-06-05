package app.sport.dao;

import app.sport.entities.StatutAccreditation;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class StatutAccreditationDAO {

    public boolean ajouter(StatutAccreditation statutAccreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(statutAccreditation);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean modifier(StatutAccreditation statutAccreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(statutAccreditation);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(StatutAccreditation statutAccreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(statutAccreditation);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            isGood = false;
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public StatutAccreditation get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        StatutAccreditation statutAccreditation = (StatutAccreditation) session.get(StatutAccreditation.class, id);
        if (statutAccreditation == null) {
            throw new RuntimeException();
        } else {
            initialiser(statutAccreditation);
        }

        session.getTransaction().commit();
        session.close();

        return statutAccreditation;
    }

    public List<StatutAccreditation> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<StatutAccreditation> statutAccreditations = session.createCriteria(StatutAccreditation.class).list();
        for (StatutAccreditation statutAccreditation : statutAccreditations) {
            initialiser(statutAccreditation);
        }

        session.getTransaction().commit();
        session.close();

        return statutAccreditations;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(StatutAccreditation.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public void initialiser(StatutAccreditation statutAccreditation) {
        Hibernate.initialize(statutAccreditation.getIndividus());
    }
}
