package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Human;
import model.HumanType;

/**
 *
 * @author HP
 */
public class HumanDAO extends DBContext {

    public HumanDAO() throws SQLException {
    // Your code here
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
            System.out.println(ex);
        }

        return humans;
    }

    public static void main(String[] args) throws SQLException {
        HumanDAO d = new HumanDAO();
        List<Human> h = d.getHumans();
        System.out.println(h.get(3).getName());
    }
}
