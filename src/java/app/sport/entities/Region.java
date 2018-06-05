package app.sport.entities;
// Generated 15 mai 2018 16:48:03 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Region generated by hbm2java
 */
@Entity
@Table(name="region"
    ,catalog="appsport"
)
public class Region  implements java.io.Serializable {


     private Integer idregion;
     private String libelle;
     private String code;
     private Set<Individu> individus = new HashSet<Individu>(0);
     private Set<Organisation> organisations = new HashSet<Organisation>(0);

    public Region() {
    }

    public Region(String libelle, String code, Set<Individu> individus, Set<Organisation> organisations) {
       this.libelle = libelle;
       this.code = code;
       this.individus = individus;
       this.organisations = organisations;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idregion", unique=true, nullable=false)
    public Integer getIdregion() {
        return this.idregion;
    }
    
    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    
    @Column(name="libelle", length=45)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    @Column(name="code", length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
    public Set<Individu> getIndividus() {
        return this.individus;
    }
    
    public void setIndividus(Set<Individu> individus) {
        this.individus = individus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
    public Set<Organisation> getOrganisations() {
        return this.organisations;
    }
    
    public void setOrganisations(Set<Organisation> organisations) {
        this.organisations = organisations;
    }




}


