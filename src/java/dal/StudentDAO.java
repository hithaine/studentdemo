package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Major;
import model.Student;

public class StudentDAO extends DBContext {
    
    //get controller

    public Connection getConnection() {
        return connection;
    }
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.rollcode, s.fullname, s.dob, s.gender, m.rollcode, m.major "
                    + "FROM Student s INNER JOIN Major m ON s.rollcode = m.rollcode";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("rollcode"));
                s.setName(rs.getString("fullname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getString("gender"));

                Major m = new Major();
                m.setId(rs.getInt("rollcode"));
                m.setMajor(rs.getString("major"));
                s.setMajor(m);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    public Student getStudentById(int id) {
        Student s = null;
        try {
            String sql = "SELECT s.rollcode, s.fullname, s.dob, s.gender, m.rollcode, m.major "
                    + "FROM Student s INNER JOIN Major m ON s.rollcode = m.rollcode "
                    + "WHERE s.rollcode = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("rollcode"));
                s.setName(rs.getString("fullname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getString("gender"));

                Major m = new Major();
                m.setId(rs.getInt("rollcode"));
                m.setMajor(rs.getString("major"));
                s.setMajor(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public int getNextStudentId() throws SQLException {
        String sql = "SELECT MAX(rollcode) FROM student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }
    
    //insert controller

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO [dbo].[Student] ([rollcode], [fullname], [dob], [gender]) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getId());
        statement.setString(2, student.getName());
        statement.setDate(3, student.getDob());
        statement.setString(4, student.getGender());

        statement.executeUpdate();

    }

    //sort controller
    public List<Student> sortStudentByGender() {
        List<Student> students = getStudents();
        students.sort((s1, s2) -> s1.getGender().compareTo(s2.getGender()));
        return students;
    }

    public List<Student> sortStudentByMajor() {
        List<Student> students = getStudents();
        students.sort((s1, s2) -> s1.getMajor().getMajor().compareTo(s2.getMajor().getMajor()));
        return students;
    }

    public List<Student> sortStudentById() {
        List<Student> students = getStudents();
        students.sort((s1, s2) -> s1.getId() - s2.getId());
        return students;
    }
}
