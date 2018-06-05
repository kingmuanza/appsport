package app.sport.servlets;

import app.sport.dao.OrganisationDAO;
import app.sport.dao.UtilisateurDAO;
import app.sport.dao.UtilisateurProfilDAO;
import app.sport.entities.Organisation;
import app.sport.entities.Utilisateur;
import app.sport.entities.UtilisateurProfil;
import app.sport.utils.BlobToString;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="UtilisateurServlet", urlPatterns={"/UtilisateurServlet"})
public class UtilisateurServlet extends HttpServlet {
    OrganisationDAO organisationDAO = new OrganisationDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO(); 
    UtilisateurProfilDAO utilisateurProfilDAO = new UtilisateurProfilDAO(); 
    BlobToString blobToString = new BlobToString();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("u", utilisateurDAO.get(i));
        }
        
        request.setAttribute("organisations", organisationDAO.getall());
        request.setAttribute("utilisateurProfils", utilisateurProfilDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/utilisateur.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Utilisateur u = new Utilisateur();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            u = utilisateurDAO.get(i);
        }
        String noms = request.getParameter("noms");
        u.setNoms(noms);
        String prenoms = request.getParameter("prenoms");
        u.setPrenoms(prenoms);
        String login = request.getParameter("login");
        u.setLogin(login);
        String passe = request.getParameter("passe");
        u.setPasse(passe);
        String imgblob = request.getParameter("imgblob");
        byte[] myBytes = imgblob.getBytes("UTF-8");
        u.setPhoto(myBytes);
        UtilisateurProfil up = null;
        String profil = request.getParameter("profil");
        if(profil!=null && !profil.isEmpty()){
            int i = Integer.parseInt(profil);
            up = utilisateurProfilDAO.get(i);
        }
        u.setUtilisateurProfil(up);
        Organisation o = null ;
        String organisation = request.getParameter("organisation");
        if(organisation!=null && !organisation.isEmpty()){
            int i = Integer.parseInt(organisation);
            o = organisationDAO.get(i);
        }
        u.setOrganisation(o);
        
        if (id != null && !id.isEmpty()) {
            utilisateurDAO.modifier(u);
        }else{
            utilisateurDAO.ajouter(u);
        }
        
        response.sendRedirect("start#!/utilisateurs");
        
    }

    
}
