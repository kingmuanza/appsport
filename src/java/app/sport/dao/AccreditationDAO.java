package app.sport.dao;

import app.sport.entities.Accreditation;
import app.sport.entities.Competition;
import app.sport.entities.Individu;
import app.sport.entities.Utilisateur;
import app.sport.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class AccreditationDAO {

    public boolean ajouter(Accreditation accreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(accreditation);
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

    public boolean modifier(Accreditation accreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(accreditation);
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

    public boolean supprimer(Accreditation accreditation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(accreditation);
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

    public Accreditation get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Accreditation accreditation = (Accreditation) session.get(Accreditation.class, id);
        if (accreditation == null) {
            throw new RuntimeException();
        } else {
            initialiser(accreditation);
        }

        session.getTransaction().commit();
        session.close();

        return accreditation;
    }

    public List<Accreditation> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Accreditation> accreditations = session.createCriteria(Accreditation.class).list();
        for (Accreditation accreditation : accreditations) {
            initialiser(accreditation);
        }

        session.getTransaction().commit();
        session.close();

        return accreditations;

    }
    public List<Accreditation> getall(Utilisateur u) {

        if(u.getUtilisateurProfil().getIdutilisateurProfil()==1){
            return getall();
        }
        List<Accreditation> accreditations = new ArrayList<>();
        for (Accreditation a : getall()) {
            if(a.getIndividu().getRegion()!=null && Objects.equals(a.getIndividu().getRegion().getIdregion(), u.getOrganisation().getRegion().getIdregion())){
                accreditations.add(a);
            }
        }

        return accreditations;

    }
    public List<Accreditation> getall(Competition competition, Utilisateur u) {

        List<Accreditation> accreditations = new ArrayList<>();
        for (Accreditation accreditation : getall(u)) {
            if(accreditation.getCompetition().getIdcompetition()==competition.getIdcompetition()){
                accreditations.add(accreditation);            }
        }

        return accreditations;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Accreditation.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public void initialiser(Accreditation accreditation) {
        Hibernate.initialize(accreditation.getIndividu());
        if (accreditation.getIndividu() != null) {
            Hibernate.initialize(accreditation.getIndividu().getIndividuPhoto());
            Hibernate.initialize(accreditation.getIndividu().getOrganisation());
            Hibernate.initialize(accreditation.getIndividu().getRegion());
            Hibernate.initialize(accreditation.getIndividu().getQualite());
            Hibernate.initialize(accreditation.getIndividu().getIndividuDisciplines());
            Hibernate.initialize(accreditation.getIndividu().getCategorie());

        }
        Hibernate.initialize(accreditation.getStatutAccreditation());
        Hibernate.initialize(accreditation.getCompetition());
        Hibernate.initialize(accreditation.getDiscipline());
    }
}
