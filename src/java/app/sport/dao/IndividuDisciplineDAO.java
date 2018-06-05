package app.sport.dao;

import app.sport.entities.IndividuDiscipline;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class IndividuDisciplineDAO {
    
    public boolean ajouter(IndividuDiscipline individuDiscipline) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(individuDiscipline);
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

    public boolean modifier(IndividuDiscipline individuDiscipline) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(individuDiscipline);
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

    public boolean supprimer(IndividuDiscipline individuDiscipline) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(individuDiscipline);
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

    public IndividuDiscipline get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        IndividuDiscipline individuDiscipline = (IndividuDiscipline) session.get(IndividuDiscipline.class, id);
        if (individuDiscipline == null) {
            throw new RuntimeException();
        } else {
            initialiser(individuDiscipline);
        }

        session.getTransaction().commit();
        session.close();

        return individuDiscipline;
    }

    public List<IndividuDiscipline> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<IndividuDiscipline> individuDisciplines = session.createCriteria(IndividuDiscipline.class).list();
        for(IndividuDiscipline individuDiscipline : individuDisciplines){
            initialiser(individuDiscipline);
        }

        session.getTransaction().commit();
        session.close();

        return individuDisciplines ;

    }
    
    public void initialiser(IndividuDiscipline individuDiscipline){
        
    }
}
