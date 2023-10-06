package controller;

import dal.MajorDAO;
import dal.StudentDAO;
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
import model.Student;
import model.Major;

/**
 *
 * @author HP
 */
@WebServlet(name = "InsertController", urlPatterns = {"/insert"})
public class InsertController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO studentDao = new StudentDAO();
        MajorDAO majorDao = new MajorDAO();
        List<Major> majors = majorDao.getMajors();
        request.setAttribute("majors", majors);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO studentDao = new StudentDAO();
        MajorDAO majorDao = new MajorDAO();
        // Get the student's information from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");
        // Create a new Student object
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setDob(dob);
        s.setGender(gender);
        
        //create a new Major object which related to new Student
        Major m = new Major();
        m.setId(id);
        m.setMajor(major);     
        
        //insert new student and major
        try {
            // Insert the student into the database
            studentDao.insertStudent(s);
            majorDao.insertMajor(m);
        } catch (SQLException ex) {
            Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);       
        }

        // Get the updated list of students
        List<Student> students = studentDao.getStudents();

        // Set the students attribute in the request
        request.setAttribute("students", students);

        // Redirect the user to the list.jsp page
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
