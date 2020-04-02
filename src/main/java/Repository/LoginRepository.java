package Repository;

import option.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public String queryByNameForPassword(String name) {
        String sql = "SELECT password FROM user WHERE name = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        String res = null;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, name);
            rs = pt.executeQuery();
            while (rs.next()) {
                res = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return res;
    }
}
