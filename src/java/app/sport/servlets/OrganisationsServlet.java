package app.sport.servlets;

import app.sport.dao.OrganisationDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="OrganisationsServlet", urlPatterns={"/OrganisationsServlet"})
public class OrganisationsServlet extends HttpServlet {
   
    OrganisationDAO organisationDAO = new OrganisationDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("temps", new Date().getTime());
        request.setAttribute("organisations", organisationDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/organisations.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
