package app.sport.dao;

import app.sport.entities.Accreditation;
import app.sport.entities.Competition;
import app.sport.entities.Discipline;
import app.sport.entities.Individu;
import app.sport.entities.IndividuDiscipline;
import app.sport.entities.Qualite;
import app.sport.entities.Region;
import app.sport.entities.Utilisateur;
import app.sport.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class IndividuDAO {

    public boolean ajouter(Individu individu) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(individu);
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

    public boolean modifier(Individu individu) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(individu);
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

    public boolean supprimer(Individu individu) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(individu);
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

    public Individu get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Individu individu = (Individu) session.get(Individu.class, id);
        if (individu == null) {
            throw new RuntimeException();
        } else {
            initialiser(individu);
        }

        session.getTransaction().commit();
        session.close();

        return individu;
    }
    public Individu getFort(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Individu individu = (Individu) session.get(Individu.class, id);
        if (individu == null) {
            throw new RuntimeException();
        } else {
            initialiser(individu);
            Hibernate.initialize(individu.getIndividuPhoto());
        }

        session.getTransaction().commit();
        session.close();

        return individu;
    }

    public List<Individu> rechercher(Utilisateur u, String nom, Discipline di, Region r, Qualite q, String genre) {
        
        System.out.println("Recherche des individus");
        
        boolean genreIs = (genre != null && !genre.isEmpty());
        boolean genreContains = false;
        if(nom!=null){
            nom = nom.toLowerCase();
        }
        
        boolean nomIs = (nom != null && !nom.isEmpty());
        boolean nomContains = false;
        boolean diIs = (di != null);
        boolean diContains = false;
        boolean rIs = (r != null);
        boolean rContains = false;
        boolean qIs = (q != null);
        boolean qContains = false;

        List<Individu> resultats = new ArrayList<Individu>();
        List<Individu> individus = getall(u);
        for (Individu individu : individus) {
            nomContains = !nomIs || (nomIs && (individu.getNom().toLowerCase().contains(nom) || individu.getPrenom().toLowerCase().contains(nom) || individu.getMatricule().toLowerCase().contains(nom)));
            qContains = !qIs || (qIs && Objects.equals(individu.getQualite().getIdqualite(), q.getIdqualite()));
            rContains = !rIs || (rIs && individu.getRegion().getIdregion() == r.getIdregion());
            diContains = false;
            genreContains = !genreIs || (genreIs && (individu.getGenre() == (genre.equals("0"))));
            if (nomContains && rContains && qContains && genreContains) {
                initialiser(individu);
                if (diIs) {
                    for (IndividuDiscipline idi : individu.getIndividuDisciplines()) {
                        if (idi.getDiscipline().getIddiscipline() == di.getIddiscipline()) {
                            diContains = true;
                        }
                    }
                }else{
                    diContains = true;
                }
                if (diContains) {
                    resultats.add(individu);
                }

            }
        }

        return resultats;
    }

    public List<Individu> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individus = session.createCriteria(Individu.class).list();
        for (Individu individu : individus) {
            initialiser(individu);
        }

        session.getTransaction().commit();
        session.close();

        return individus;

    }
    public List<Individu> getall(Utilisateur u) {
        
        if(u.getUtilisateurProfil().getIdutilisateurProfil()==1){
            return getall();
        }
        
        List<Individu> individus = new ArrayList<>();
        for (Individu individu : getall()) {
            if(individu.getRegion()!=null && Objects.equals(individu.getRegion().getIdregion(), u.getOrganisation().getRegion().getIdregion())){
                individus.add(individu);
            }
        }

        return individus;

    }
    
    public boolean isAccredited(Individu i, Competition c){
        for(Accreditation a : i.getAccreditations()){
            if(c!=null && a.getCompetition().getIdcompetition()==c.getIdcompetition()){
                return true ;
            }
        }
        return false ;
    }
    
    public List<Individu> getAllNonAccredite(Utilisateur u, Competition c){
        List<Individu> individus = new ArrayList<>();
        for(Individu i : getall(u)){
            if(!isAccredited(i, c)){
                individus.add(i);
            }
        }
        return individus ;
    }
    
    
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Individu.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Individu individu) {
        Hibernate.initialize(individu.getQualite());
        Hibernate.initialize(individu.getStatutAccreditation());
        Hibernate.initialize(individu.getRegion());
        Hibernate.initialize(individu.getIndividuDisciplines());
        for (IndividuDiscipline id : individu.getIndividuDisciplines()) {
            Hibernate.initialize(id.getDiscipline());
            Hibernate.initialize(id.getIndividu());
        }
        Hibernate.initialize(individu.getRegion());
        //Hibernate.initialize(individu.getIndividuPhoto());
        Hibernate.initialize(individu.getQualite());
        Hibernate.initialize(individu.getAccreditations());
        Hibernate.initialize(individu.getOrganisation());
    }
}
