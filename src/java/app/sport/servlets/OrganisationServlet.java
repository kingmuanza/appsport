package app.sport.servlets;

import app.sport.dao.OrganisationDAO;
import app.sport.dao.RegionDAO;
import app.sport.entities.Organisation;
import app.sport.entities.Region;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrganisationServlet", urlPatterns = {"/OrganisationServlet"})
public class OrganisationServlet extends HttpServlet {

    OrganisationDAO organisationDAO = new OrganisationDAO();
    RegionDAO regionDAO = new RegionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int i = Integer.parseInt(id);
            request.setAttribute("organisation", organisationDAO.get(i));
        }
        request.setAttribute("regions", regionDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/organisation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String option = "";
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            int i = Integer.parseInt(id);
            Organisation d = organisationDAO.get(i);
            try {
                organisationDAO.supprimer(d);
                option = "option=true";
            } catch (Exception e) {
                option = "option=false";
            }
        } else {

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String idregion = request.getParameter("region");

            Region region = null;
            if (idregion != null && !idregion.isEmpty()) {
                int i = Integer.parseInt(idregion);
                region = regionDAO.get(i);
            }

            Organisation d = new Organisation();
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                d = organisationDAO.get(i);
            }

            d.setCode(code);
            d.setLibelle(libelle);
            d.setRegion(region);
            

            if (id != null && !id.isEmpty()) {
                organisationDAO.modifier(d);
            } else {
                organisationDAO.ajouter(d);
            }
            option = "option=true";

        }
        response.sendRedirect("start#!/organisations?" + option);
    }

}
