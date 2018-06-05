package app.sport.dao;

import app.sport.entities.Athlete;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class AthleteDAO {
    
    public boolean ajouter(Athlete athlete) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(athlete);
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

    public boolean modifier(Athlete athlete) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(athlete);
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

    public boolean supprimer(Athlete athlete) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(athlete);
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

    public Athlete get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Athlete athlete = (Athlete) session.get(Athlete.class, id);
        if (athlete == null) {
            throw new RuntimeException();
        } else {
            initialiser(athlete);
        }

        session.getTransaction().commit();
        session.close();

        return athlete;
    }

    public List<Athlete> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Athlete> athletes = session.createCriteria(Athlete.class).list();
        for(Athlete athlete : athletes){
            initialiser(athlete);
        }

        session.getTransaction().commit();
        session.close();

        return athletes ;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Athlete.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public void initialiser(Athlete athlete){
        
    }
}
