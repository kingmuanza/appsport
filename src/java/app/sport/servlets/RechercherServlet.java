package app.sport.servlets;

import app.sport.dao.DisciplineDAO;
import app.sport.dao.IndividuDAO;
import app.sport.dao.IndividuDisciplineDAO;
import app.sport.dao.QualiteDAO;
import app.sport.dao.RegionDAO;
import app.sport.entities.Discipline;
import app.sport.entities.IndividuDiscipline;
import app.sport.entities.Qualite;
import app.sport.entities.Region;
import app.sport.entities.Utilisateur;
import app.sport.utils.BlobToString;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RechercherServlet", urlPatterns = {"/recherche"})
public class RechercherServlet extends HttpServlet {

    QualiteDAO qualiteDAO = new QualiteDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    RegionDAO regionDAO = new RegionDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO();
    IndividuDisciplineDAO individuDisciplineDAO = new IndividuDisciplineDAO();
    BlobToString blobToString = new BlobToString();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String genre = request.getParameter("genre");
            request.setAttribute("genre", genre);
            String region = request.getParameter("region");
            Region r = null;
            if (region != null && !region.isEmpty()) {
                int i = Integer.parseInt(region);
                r = regionDAO.get(i);
            }
            request.setAttribute("region", r);
            String qualite = request.getParameter("qualite");
            Qualite q = null;
            if (qualite != null && !qualite.isEmpty()) {
                int i = Integer.parseInt(qualite);
                q = qualiteDAO.get(i);
            }
            request.setAttribute("qualite", q);
            String discipline = request.getParameter("discipline");
            Discipline di = null;
            if (discipline != null && !discipline.isEmpty()) {
                int i = Integer.parseInt(discipline);
                di = disciplineDAO.get(i);
            }
            request.setAttribute("discipline", di);
            String nom = request.getParameter("nom");
            request.setAttribute("individus", individuDAO.rechercher(utilisateur, nom, di, r, q, genre));
            request.setAttribute("qualites", qualiteDAO.getall());
            request.setAttribute("regions", regionDAO.getall());
            request.setAttribute("disciplines", disciplineDAO.getall());
            request.setAttribute("blobToString", blobToString);
            request.setAttribute("nom", nom);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/individus.jsp").forward(request, response);
        } else {

            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
