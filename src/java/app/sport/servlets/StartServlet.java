package app.sport.servlets;

import app.sport.dao.CategorieDAO;
import app.sport.dao.CompetitionDAO;
import app.sport.dao.DisciplineDAO;
import app.sport.dao.IndividuDAO;
import app.sport.dao.OrganisationDAO;
import app.sport.dao.QualiteDAO;
import app.sport.dao.RegionDAO;
import app.sport.dao.UtilisateurDAO;
import app.sport.entities.Utilisateur;
import app.sport.utils.BlobToString;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "StartServlet", urlPatterns = {"/start"})
public class StartServlet extends HttpServlet {
    
    QualiteDAO qualiteDAO = new QualiteDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    RegionDAO regionDAO = new RegionDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO();
    CompetitionDAO competitionDAO = new CompetitionDAO();
    CategorieDAO categorieDAO = new CategorieDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    OrganisationDAO organisationDAO = new OrganisationDAO();
    BlobToString blobToString = new BlobToString();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String error = request.getParameter("error");
            String option = request.getParameter("option");
            System.out.println("option : "+option);
            if(error!=null && !error.isEmpty()){
                request.setAttribute("error", error);
            }
            if(option!=null && !option.isEmpty()){
                request.setAttribute("option", option);
                System.out.println("option : "+option);
            }
            request.setAttribute("qualites", qualiteDAO.getNumber());
            request.setAttribute("regions", regionDAO.getNumber());
            request.setAttribute("disciplines", disciplineDAO.getNumber());
            request.setAttribute("individus", individuDAO.getall(utilisateur).size());
            request.setAttribute("utilisateurs", utilisateurDAO.getNumber());
            request.setAttribute("competitions", competitionDAO.getNumber());
            request.setAttribute("categories", categorieDAO.getNumber());
            request.setAttribute("organisations", organisationDAO.getNumber());
            request.setAttribute("blobToString", blobToString);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/start.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
