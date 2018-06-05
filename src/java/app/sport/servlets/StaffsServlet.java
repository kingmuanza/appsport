package app.sport.servlets;

import app.sport.dao.CategorieDAO;
import app.sport.dao.DisciplineDAO;
import app.sport.dao.IndividuDAO;
import app.sport.dao.QualiteDAO;
import app.sport.dao.RegionDAO;
import app.sport.entities.Utilisateur;
import app.sport.utils.BlobToString;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "StaffsServlet", urlPatterns = {"/StaffsServlet"})
public class StaffsServlet extends HttpServlet {
    QualiteDAO qualiteDAO = new QualiteDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    CategorieDAO categorieDAO = new CategorieDAO();
    RegionDAO regionDAO = new RegionDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO();
    BlobToString blobToString = new BlobToString();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        request.setAttribute("qualites", qualiteDAO.getall());
        request.setAttribute("regions", regionDAO.getall());
        request.setAttribute("categories", categorieDAO.getall());
        request.setAttribute("disciplines", disciplineDAO.getall());
        request.setAttribute("individus", individuDAO.getall(utilisateur));
        request.setAttribute("blobToString", blobToString);
        request.setAttribute("temps", new Date().getTime());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/individus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
