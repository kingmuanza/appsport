package app.sport.servlets;

import app.sport.dao.CategorieDAO;
import app.sport.dao.DisciplineDAO;
import app.sport.entities.Categorie;
import app.sport.entities.Discipline;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategorieServlet", urlPatterns = {"/CategorieServlet"})
public class CategorieServlet extends HttpServlet {

    CategorieDAO categorieDAO = new CategorieDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("categorie", categorieDAO.get(i));
        }
        request.setAttribute("disciplines", disciplineDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/categorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Categorie d = categorieDAO.get(i);
            try {
                categorieDAO.supprimer(d);
                option = "option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String iddiscpline = request.getParameter("discipline");

            Discipline discipline = null;
            if (iddiscpline != null && !iddiscpline.isEmpty()) {
                int i = Integer.parseInt(iddiscpline);
                discipline = disciplineDAO.get(i);
            }

            Categorie d = new Categorie();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = categorieDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);
            d.setDiscipline(discipline);

            if (id != null && !id.isEmpty()) {
                categorieDAO.modifier(d);
            } else {
                categorieDAO.ajouter(d);
            }
            option = "option=true";

        }
        response.sendRedirect("start#!/categories?" + option);
    }

}
