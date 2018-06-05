package app.sport.entities;
// Generated 15 mai 2018 16:48:03 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name="utilisateur"
    ,catalog="appsport"
)
public class Utilisateur  implements java.io.Serializable {


     private Integer idutilisateur;
     private Organisation organisation;
     private UtilisateurProfil utilisateurProfil;
     private String login;
     private String passe;
     private String noms;
     private String prenoms;
     private String qualite;
     private byte[] photo;

    public Utilisateur() {
    }

    public Utilisateur(Organisation organisation, UtilisateurProfil utilisateurProfil, String login, String passe, String noms, String prenoms, String qualite, byte[] photo) {
       this.organisation = organisation;
       this.utilisateurProfil = utilisateurProfil;
       this.login = login;
       this.passe = passe;
       this.noms = noms;
       this.prenoms = prenoms;
       this.qualite = qualite;
       this.photo = photo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idutilisateur", unique=true, nullable=false)
    public Integer getIdutilisateur() {
        return this.idutilisateur;
    }
    
    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorganisation")
    public Organisation getOrganisation() {
        return this.organisation;
    }
    
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idutilisateur_profil")
    public UtilisateurProfil getUtilisateurProfil() {
        return this.utilisateurProfil;
    }
    
    public void setUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfil = utilisateurProfil;
    }

    
    @Column(name="login", length=45)
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    
    @Column(name="passe", length=45)
    public String getPasse() {
        return this.passe;
    }
    
    public void setPasse(String passe) {
        this.passe = passe;
    }

    
    @Column(name="noms", length=45)
    public String getNoms() {
        return this.noms;
    }
    
    public void setNoms(String noms) {
        this.noms = noms;
    }

    
    @Column(name="prenoms", length=45)
    public String getPrenoms() {
        return this.prenoms;
    }
    
    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    
    @Column(name="qualite", length=45)
    public String getQualite() {
        return this.qualite;
    }
    
    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    
    @Column(name="photo")
    public byte[] getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }




}

