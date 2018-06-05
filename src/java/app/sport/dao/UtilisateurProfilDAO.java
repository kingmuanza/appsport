package app.sport.dao;

import app.sport.entities.UtilisateurProfil;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class UtilisateurProfilDAO {
    
    public boolean ajouter(UtilisateurProfil utilisateurProfil) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(utilisateurProfil);
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

    public boolean modifier(UtilisateurProfil utilisateurProfil) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(utilisateurProfil);
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

    public boolean supprimer(UtilisateurProfil utilisateurProfil) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateurProfil);
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

    public UtilisateurProfil get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfil utilisateurProfil = (UtilisateurProfil) session.get(UtilisateurProfil.class, id);
        if (utilisateurProfil == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateurProfil);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfil;
    }

    public List<UtilisateurProfil> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfil> utilisateurProfils = session.createCriteria(UtilisateurProfil.class).list();
        for(UtilisateurProfil utilisateurProfil : utilisateurProfils){
            initialiser(utilisateurProfil);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfils ;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(UtilisateurProfil.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public void initialiser(UtilisateurProfil utilisateurProfil){
        
    }
}
