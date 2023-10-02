
package controller.human;

import dal.DBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Human;
import model.HumanType;


@WebServlet(name="EditController", urlPatterns={"/edit"})
public class EditController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DBContext db = new DBContext();
        List<HumanType> types = db.getTypes();
        String id = request.getParameter("id");
        
        request.setAttribute("id", id);
        request.setAttribute("types", types);
        
        request.getRequestDispatcher("edit.jsp").forward(request, response);  
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DBContext db = new DBContext();
        // Get the human's information from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        HumanType type = db.getHumanTypeByName(request.getParameter("type"));

        // Create a new Human object
        Human h = new Human();
        h.setId(id);
        h.setName(name);
        h.setDob(dob);
        h.setGender(gender);
        h.setType(type);
        
        //update new human
        try {
            // Update the human into the database
            db.updateHuman(h);
        } catch (SQLException ex) {
            Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);       
        }

        // Get the updated list of humans
        List<Human> humans = db.getHumans();

        // Set the humans attribute in the request
        request.setAttribute("humans", humans);

        // Redirect the user to the list.jsp page
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
