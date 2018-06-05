package app.sport.dao;

import app.sport.entities.Discipline;
import app.sport.entities.Utilisateur;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UtilisateurDAO {

    public Utilisateur get(String login, String passe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Utilisateur utilisateur = null;
        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class)
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("passe", passe))
                .list();

        if (utilisateurs.isEmpty()) {

        } else {
            utilisateur = utilisateurs.get(0);
            initialiser(utilisateur);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateur;
    }

    public boolean ajouter(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(utilisateur);
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

    public boolean modifier(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(utilisateur);
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

    public boolean supprimer(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateur);
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

    public Utilisateur get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
        if (utilisateur == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateur);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateur;
    }

    public List<Utilisateur> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class).list();
        for (Utilisateur utilisateur : utilisateurs) {
            initialiser(utilisateur);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurs;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Utilisateur.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public void initialiser(Utilisateur utilisateur) {
        Hibernate.initialize(utilisateur.getUtilisateurProfil());
        Hibernate.initialize(utilisateur.getOrganisation());
        if (utilisateur.getOrganisation() != null) {
            Hibernate.initialize(utilisateur.getOrganisation().getRegion());

        }
    }
}
