package app.sport.dao;

import app.sport.entities.Categorie;
import app.sport.entities.Discipline;
import app.sport.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class CategorieDAO {

    public boolean ajouter(Categorie categorie) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(categorie);
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

    public boolean modifier(Categorie categorie) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(categorie);
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

    public boolean supprimer(Categorie categorie) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(categorie);
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

    public Categorie get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Categorie categorie = (Categorie) session.get(Categorie.class, id);
        if (categorie == null) {
            throw new RuntimeException();
        } else {
            initialiser(categorie);
        }

        session.getTransaction().commit();
        session.close();

        return categorie;
    }

    public List<Categorie> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Categorie> categories = session.createCriteria(Categorie.class).list();
        for (Categorie categorie : categories) {
            initialiser(categorie);
        }

        session.getTransaction().commit();
        session.close();

        return categories;

    }

    public List<Categorie> getAllByDisciplines(Discipline discipline) {
        List<Categorie> categories = new ArrayList<Categorie>();
        for (Categorie categorie : getall()) {
            if (categorie.getDiscipline().getIddiscipline() == discipline.getIddiscipline()) {
                categories.add(categorie);
            }
        }
        return categories;
    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Categorie.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public void initialiser(Categorie categorie) {
        Hibernate.initialize(categorie.getDiscipline());
    }
}
