package app.sport.dao;

import app.sport.entities.Organisation;
import app.sport.entities.Region;
import app.sport.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class RegionDAO {
    
    public boolean ajouter(Region region) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(region);
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

    public boolean modifier(Region region) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.update(region);
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

    public boolean supprimer(Region region) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(region);
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

    public Region get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Region region = (Region) session.get(Region.class, id);
        if (region == null) {
            throw new RuntimeException();
        } else {
            initialiser(region);
        }

        session.getTransaction().commit();
        session.close();

        return region;
    }

    public List<Region> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Region> regions = session.createCriteria(Region.class).list();
        for(Region region : regions){
            initialiser(region);
        }

        session.getTransaction().commit();
        session.close();

        return regions ;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Region.class).setProjection(Projections.rowCount()).uniqueResult() ;
        

        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public void initialiser(Region region){
        Hibernate.initialize(region.getOrganisations());
        if(region.getOrganisations()!=null){
            for(Organisation o : region.getOrganisations()){
                Hibernate.initialize(o.getUtilisateurs());
            }
        }
    }
}
