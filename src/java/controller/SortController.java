package controller;

import dal.StudentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Student;

@WebServlet(name = "SortController", urlPatterns = {"/sort"})
public class SortController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("sort.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO studentDao = new StudentDAO();
        String sortBy = request.getParameter("sortby");
        switch (sortBy) {
            case "gender":
                {
                    List<Student> students = studentDao.sortStudentByGender();
                    request.setAttribute("students", students);
                    request.getRequestDispatcher("list.jsp").forward(request, response);
                    break;
                }
            case "major":
                {
                    List<Student> students = studentDao.sortStudentByMajor();
                    request.setAttribute("students", students);
                    request.getRequestDispatcher("list.jsp").forward(request, response);
                    break;
                }
            case "id":
                {
                        List<Student> students = studentDao.sortStudentById();
                        request.setAttribute("students", students);
                        request.getRequestDispatcher("list.jsp").forward(request, response);
                    break;
                }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
