package app.sport.servlets;

import app.sport.dao.IndividuDAO;
import app.sport.dao.IndividuPhotoDAO;
import app.sport.utils.BlobToString;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="IndividuPhotoServlet", urlPatterns={"/IndividuPhotoServlet"})
public class IndividuPhotoServlet extends HttpServlet {
   
    IndividuDAO individuDAO = new IndividuDAO();
    BlobToString blobToString = new BlobToString();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id!=null && !id.isEmpty()){
            int i = Integer.parseInt(id);
            request.setAttribute("blobToString", blobToString);
            System.out.println(i);
            request.setAttribute("photo", individuDAO.getFort(i).getIndividuPhoto());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajax/photo.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
