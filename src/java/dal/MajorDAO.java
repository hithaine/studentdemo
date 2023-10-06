package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Major;

public class MajorDAO extends DBContext {

    public List<Major> getMajors() {
        List<Major> majors = new ArrayList<>();
        try {
            String sql = "SELECT m.rollcode, m.major "
                    + "FROM Major m";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Major m = new Major();
                m.setId(rs.getInt("rollcode"));
                m.setMajor(rs.getString("major"));
                majors.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return majors;
    }

    public Major getMajorByName(String name) {
        Major m = null;
        try {
            String sql = "SELECT [rollcode], [major] "
                    + "FROM [Major]"
                    + "WHERE [major] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                m = new Major();
                m.setId(rs.getInt("rollcode"));
                m.setMajor(rs.getString("major"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public void insertMajor(Major major) throws SQLException {
        String sql = "INSERT INTO [dbo].[Major] ([rollcode], [major]) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, major.getId());
        statement.setString(2, major.getMajor());

        statement.executeUpdate();
    }
}
