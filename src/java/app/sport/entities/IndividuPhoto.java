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
 * IndividuPhoto generated by hbm2java
 */
@Entity
@Table(name="individu_photo"
    ,catalog="appsport"
)
public class IndividuPhoto  implements java.io.Serializable {


     private Integer idindividuPhoto;
     private byte[] src;
     private Integer poids;
     private Set<Individu> individus = new HashSet<Individu>(0);

    public IndividuPhoto() {
    }

    public IndividuPhoto(byte[] src, Integer poids, Set<Individu> individus) {
       this.src = src;
       this.poids = poids;
       this.individus = individus;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idindividu_photo", unique=true, nullable=false)
    public Integer getIdindividuPhoto() {
        return this.idindividuPhoto;
    }
    
    public void setIdindividuPhoto(Integer idindividuPhoto) {
        this.idindividuPhoto = idindividuPhoto;
    }

    
    @Column(name="src")
    public byte[] getSrc() {
        return this.src;
    }
    
    public void setSrc(byte[] src) {
        this.src = src;
    }

    
    @Column(name="poids")
    public Integer getPoids() {
        return this.poids;
    }
    
    public void setPoids(Integer poids) {
        this.poids = poids;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="individuPhoto")
    public Set<Individu> getIndividus() {
        return this.individus;
    }
    
    public void setIndividus(Set<Individu> individus) {
        this.individus = individus;
    }




}

