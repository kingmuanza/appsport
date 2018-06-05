package app.sport.servlets;

import app.sport.dao.CategorieDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CategoriesServlet", urlPatterns={"/CategoriesServlet"})
public class CategoriesServlet extends HttpServlet {
   
    CategorieDAO categorieDAO = new CategorieDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("categories", categorieDAO.getall());
        request.setAttribute("temps", new Date().getTime());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
