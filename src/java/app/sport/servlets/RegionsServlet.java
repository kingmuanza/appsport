package app.sport.servlets;

import app.sport.dao.RegionDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="RegionsServlet", urlPatterns={"/RegionsServlet"})
public class RegionsServlet extends HttpServlet {
   
    RegionDAO regionDAO = new RegionDAO() ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("temps", new Date().getTime());
        request.setAttribute("regions", regionDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/regions.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
