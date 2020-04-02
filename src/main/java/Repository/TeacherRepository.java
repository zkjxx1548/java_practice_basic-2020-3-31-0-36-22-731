import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    public List<Teacher> queryAllTeacher() {
        String sql = "SELECT teacher_id, teacher_name FROM teacher";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getString("teacher_id"),
                        rs.getString("teacher_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return teachers;
    }

    public Teacher queryTeacherById(String id) {
        String sql = "SELECT teacher_id, teacher_name FROM teacher WHERE teacher_id = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        Teacher teacher = null;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, id);
            rs = pt.executeQuery();
            while (rs.next()) {
                teacher = new Teacher(
                        rs.getString("teacher_id"),
                        rs.getString("teacher_name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return teacher;
    }

    public void delete(String teacherId) {
        Connection conn;
        PreparedStatement pt;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM teacher WHERE teacher_id = ?";
            Teacher teacher = queryTeacherById(teacherId);
            pt = conn.prepareStatement(sql);

            pt.setString(1, teacherId);
            pt.executeUpdate();
            pt.close();
            conn.close();
            System.out.println("删除课程["+ teacher.getId() + "-" + teacher.getName() + "]成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
