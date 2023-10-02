package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Human;
import model.HumanType;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            // Edit URL, username, password to authenticate with your MS SQL Server 
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=Human";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Human;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Human> getHumans() {
        List<Human> humans = new ArrayList<>();
        try {
            String sql = "SELECT h.humanid, h.humanname, h. humandob, h. humangender, ht.typeid, ht.typename "
                    + "FROM Human h INNER JOIN HumanType ht ON h.typeid = ht.typeid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Human h = new Human();
                h.setId(rs.getInt("humanid"));
                h.setName(rs.getString("humanname"));
                h.setDob(rs.getDate("humandob"));
                h.setGender(rs.getString("humangender"));

                HumanType ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
                h.setType(ht);
                humans.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return humans;
    }

    public List<HumanType> getTypes() {
        List<HumanType> types = new ArrayList<>();
        try {
            String sql = "SELECT ht.typeid, ht.typename "
                    + "FROM HumanType ht";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HumanType ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
                types.add(ht);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return types;
    }

    public HumanType getHumanTypeByName(String name) {
        HumanType ht = null;
        try {
            String sql = "SELECT [typeid], [typename] "
                    + "FROM [HumanType]"
                    + "WHERE [typename] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ht = new HumanType();
                ht.setTypeId(rs.getInt("typeid"));
                ht.setName(rs.getString("typename"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ht;
    }

    public void insertHuman(Human human) throws SQLException {
        String sql = "INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, human.getName());
        statement.setDate(2, human.getDob());
        statement.setString(3, human.getGender());
        statement.setInt(4, human.getType().getTypeId());

        statement.executeUpdate();
    }

    public void updateHuman(Human human) throws SQLException {
        String sql = "UPDATE [Human] SET [humanname] = ?, [humandob] = ?, [humangender] = ?, [typeid] = ? WHERE [humanid] = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, human.getName());
        statement.setDate(2, human.getDob());
        statement.setString(3, human.getGender());
        statement.setInt(4, human.getType().getTypeId());
        statement.setInt(5, human.getId());

        statement.executeUpdate();
    }

    public void deleteHuman(int id) throws SQLException {
        String sql = "DELETE FROM [Human] WHERE [humanid] = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        statement.executeUpdate();
    }
}
