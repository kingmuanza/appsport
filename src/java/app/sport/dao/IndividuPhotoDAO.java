package app.sport.dao;

import app.sport.entities.IndividuPhoto;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class IndividuPhotoDAO {
    
    public boolean ajouter(IndividuPhoto individuPhoto) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(individuPhoto);
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

    public boolean modifier(IndividuPhoto individuPhoto) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(individuPhoto);
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

    public boolean supprimer(IndividuPhoto individuPhoto) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(individuPhoto);
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

    public IndividuPhoto get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        IndividuPhoto individuPhoto = (IndividuPhoto) session.get(IndividuPhoto.class, id);
        if (individuPhoto == null) {
            throw new RuntimeException();
        } else {
            initialiser(individuPhoto);
        }

        session.getTransaction().commit();
        session.close();

        return individuPhoto;
    }

    public List<IndividuPhoto> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<IndividuPhoto> individuPhotos = session.createCriteria(IndividuPhoto.class).list();
        for(IndividuPhoto individuPhoto : individuPhotos){
            initialiser(individuPhoto);
        }

        session.getTransaction().commit();
        session.close();

        return individuPhotos ;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(IndividuPhoto.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public void initialiser(IndividuPhoto individuPhoto){
        
    }
}
