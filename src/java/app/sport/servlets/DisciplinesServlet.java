package app.sport.servlets;

import app.sport.dao.DisciplineDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="DisciplinesServlet", urlPatterns={"/DisciplinesServlet"})
public class DisciplinesServlet extends HttpServlet {
   
    DisciplineDAO disciplineDAO = new DisciplineDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("disciplines", disciplineDAO.getall());
        request.setAttribute("temps", new Date().getTime());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/disciplines.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
