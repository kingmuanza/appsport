package app.sport.servlets;

import app.sport.dao.CategorieDAO;
import app.sport.dao.DisciplineDAO;
import app.sport.entities.Discipline;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CategoriresAjaxServlet", urlPatterns={"/CategoriresAjaxServlet"})
public class CategoriresAjaxServlet extends HttpServlet {
   
    CategorieDAO categorieDAO = new CategorieDAO();
    DisciplineDAO disciplineDAO = new DisciplineDAO(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            int i = Integer.parseInt(id);
            Discipline discipline = disciplineDAO.get(i);
            request.setAttribute("categories", categorieDAO.getAllByDisciplines(discipline));
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajax/categories.jsp").forward(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
