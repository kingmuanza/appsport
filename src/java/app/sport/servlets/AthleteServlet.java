package app.sport.servlets;

import app.sport.dao.DisciplineDAO;
import app.sport.dao.IndividuDAO;
import app.sport.dao.IndividuDisciplineDAO;
import app.sport.dao.IndividuPhotoDAO;
import app.sport.dao.OrganisationDAO;
import app.sport.dao.QualiteDAO;
import app.sport.dao.RegionDAO;
import app.sport.dao.StatutAccreditationDAO;
import app.sport.entities.Discipline;
import app.sport.entities.Individu;
import app.sport.entities.IndividuDiscipline;
import app.sport.entities.IndividuPhoto;
import app.sport.entities.Organisation;
import app.sport.entities.Qualite;
import app.sport.entities.Region;
import app.sport.entities.StatutAccreditation;
import app.sport.entities.Utilisateur;
import app.sport.utils.BlobToString;
import app.sport.utils.DisciplineUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AthleteServlet", urlPatterns = {"/AthleteServlet"})
public class AthleteServlet extends HttpServlet {

    QualiteDAO qualiteDAO = new QualiteDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    RegionDAO regionDAO = new RegionDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO();
    IndividuDisciplineDAO individuDisciplineDAO = new IndividuDisciplineDAO();
    BlobToString blobToString = new BlobToString();
    DisciplineUtil disciplineUtil = new DisciplineUtil();
    StatutAccreditationDAO statutAccreditationDAO = new StatutAccreditationDAO();
    OrganisationDAO organisationDAO = new OrganisationDAO();
    IndividuPhotoDAO individuPhotoDAO = new IndividuPhotoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("individu", individuDAO.get(i));
        }
        request.setAttribute("qualites", qualiteDAO.getall());
        request.setAttribute("statutAccreditations", statutAccreditationDAO.getall());
        request.setAttribute("regions", regionDAO.getall());
        request.setAttribute("disciplines", disciplineDAO.getall());
        request.setAttribute("blobToString", blobToString);
        request.setAttribute("disciplineUtil", disciplineUtil);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/individu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Individu individu = new Individu();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            individu = individuDAO.get(i);
        }
        String genre = request.getParameter("genre");
        if (genre.equals("0")) {
            individu.setGenre(Boolean.TRUE);
        } else {
            individu.setGenre(Boolean.FALSE);
        }
        String noms = request.getParameter("noms");
        individu.setNom(noms);
        String imgblob = request.getParameter("imgblob");
        byte[] myBytes = imgblob.getBytes("UTF-8");
        
        if(individu.getIndividuPhoto()!=null){
            individuPhotoDAO.supprimer(individu.getIndividuPhoto());
        }
        
        IndividuPhoto photo = new IndividuPhoto();
        photo.setSrc(myBytes);
        individuPhotoDAO.ajouter(photo);
        individu.setIndividuPhoto(photo);
        String prenoms = request.getParameter("prenoms");
        individu.setPrenom(prenoms);
        String surnom = request.getParameter("surnom");
        individu.setSurnom(surnom);
        String region = request.getParameter("region");
        Region r = null;
        if (region != null && !region.isEmpty()) {
            int i = Integer.parseInt(region);
            r = regionDAO.get(i);
        }
        individu.setRegion(r);
        String qualite = request.getParameter("qualite");
        Qualite q = null;
        if (qualite != null && !qualite.isEmpty()) {
            int i = Integer.parseInt(qualite);
            q = qualiteDAO.get(i);
        }
        individu.setQualite(q);
        
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        Organisation organisation = organisationDAO.get(utilisateur.getOrganisation().getIdorganisation());
        individu.setOrganisation(organisation);

        String accreditation = request.getParameter("accreditation");
        StatutAccreditation statutAccreditation = null;
        if (accreditation != null && !accreditation.isEmpty()) {
            int i = Integer.parseInt(accreditation);
            statutAccreditation = statutAccreditationDAO.get(i);
        }
        individu.setStatutAccreditation(statutAccreditation);

        String datenaiss = request.getParameter("datenaiss");
        System.out.println("datenaiss : " + datenaiss);
        Date date = null;
        try {
            date = sdf.parse(datenaiss);
        } catch (ParseException ex) {

        }
        individu.setDatenaiss(date);
        String lieunaiss = request.getParameter("lieunaiss");
        individu.setLieunaiss(lieunaiss);
        String taille = request.getParameter("taille");
        int tailleCM = Integer.parseInt(taille);
        individu.setTaille(tailleCM);
        String poids = request.getParameter("poids");
        int poidsKG = Integer.parseInt(poids);
        individu.setPoids(poidsKG);
        String tel = request.getParameter("tel");
        individu.setTel(tel);
        String email = request.getParameter("email");
        individu.setEmail(email);
        for (IndividuDiscipline indivdisc : individu.getIndividuDisciplines()) {
            individuDisciplineDAO.supprimer(indivdisc);
        }
        String matricule = r.getCode() + q.getCode();
        if (genre.equals("0")) {
            matricule += "MA";
        } else {
            matricule += "FE";
        }

        if (id != null && !id.isEmpty()) {
            matricule += individu.getIdindividu().toString();
            individu.setMatricule(matricule);
            individuDAO.modifier(individu);
        } else {
            individuDAO.ajouter(individu);
            matricule += individu.getIdindividu().toString();
            individu.setMatricule(matricule);
            individuDAO.modifier(individu);
        }

        String[] disciplines = request.getParameterValues("disciplines");
        if (disciplines != null && disciplines.length > 0) {
            for (String d : disciplines) {
                if (d != null && !d.isEmpty()) {
                    int i = Integer.parseInt(d);
                    Discipline di = disciplineDAO.get(i);
                    IndividuDiscipline idi = new IndividuDiscipline(di, individu);
                    individuDisciplineDAO.ajouter(idi);
                }

            }
        }
        response.sendRedirect("start");

    }

}
