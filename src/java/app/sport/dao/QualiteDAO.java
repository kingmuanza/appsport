package app.sport.dao;

import app.sport.entities.Qualite;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class QualiteDAO {
    
    public boolean ajouter(Qualite qualite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(qualite);
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

    public boolean modifier(Qualite qualite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(qualite);
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

    public boolean supprimer(Qualite qualite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(qualite);
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

    public Qualite get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Qualite qualite = (Qualite) session.get(Qualite.class, id);
        if (qualite == null) {
            throw new RuntimeException();
        } else {
            initialiser(qualite);
        }

        session.getTransaction().commit();
        session.close();

        return qualite;
    }

    public List<Qualite> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Qualite> qualites = session.createCriteria(Qualite.class).list();
        for(Qualite qualite : qualites){
            initialiser(qualite);
        }

        session.getTransaction().commit();
        session.close();

        return qualites ;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Qualite.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public void initialiser(Qualite qualite){
        
    }
}
