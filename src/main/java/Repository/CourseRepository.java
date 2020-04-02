import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    public List<Course> queryAllCourse() {
        String sql = "SELECT t1.course_id, t2.teacher_name, t3.subject_name FROM course AS t1 " +
                "INNER JOIN teacher AS t2 ON t1.teacher_id = t2.teacher_id " +
                "INNER JOIN  subject AS t3 ON t1.subject_id = t3.subject_id";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<Course> courses = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("course_id"),
                        rs.getString("teacher_name"),
                        rs.getString("subject_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return courses;
    }

    public Course queryCourseById(String id) {
        String sql = "SELECT t1.course_id, t2.teacher_name, t3.subject_name FROM course AS t1 " +
                "INNER JOIN teacher AS t2 ON t1.teacher_id = t2.teacher_id " +
                "INNER JOIN  subject AS t3 ON t1.subject_id = t3.subject_id " +
                "WHERE t1.course_id = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        Course course = null;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, id);
            rs = pt.executeQuery();
            while (rs.next()) {
                course = new Course(
                        rs.getString("course_id"),
                        rs.getString("teacher_name"),
                        rs.getString("subject_name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return course;
    }

    public List<CourseByTeacherName> queryCourseByTeacherName(String name) {
        String sql = "SELECT t1.course_id, t3.subject_name FROM course AS t1 " +
                "INNER JOIN teacher AS t2 ON t1.teacher_id = t2.teacher_id " +
                "INNER JOIN  subject AS t3 ON t1.subject_id = t3.subject_id " +
                "WHERE t2.teacher_name = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<CourseByTeacherName> courseByTeacherNames = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, name);
            rs = pt.executeQuery();
            while (rs.next()) {
                courseByTeacherNames.add(new CourseByTeacherName(
                        rs.getString("course_id"),
                        rs.getString("subject_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return courseByTeacherNames;
    }

    public void save(Course course) {
        String sql = "INSERT INTO course(course_id, teacher_id, subject_id) VALUES (?,?,?)";
        Connection conn;
        PreparedStatement pt;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);

            pt.setString(1, course.getId());
            pt.setString(2,course.getTeacherId());
            pt.setString(3, course.getSubjectId());
            pt.executeUpdate();
            pt.close();
            conn.close();
            System.out.println("添加课程[" + course.getTeacherName() + "-" +course.getSubjectName() + "-" + course.getId() + "]成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String courseId) {
        Connection conn;
        PreparedStatement pt;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM course WHERE course_id = ?";
            Course course = queryCourseById(courseId);
            pt = conn.prepareStatement(sql);

            pt.setString(1, courseId);
            pt.executeUpdate();
            pt.close();
            conn.close();
            System.out.println("删除课程["+ course.getId() + "-" + course.getTeacherName() + "-" + course.getSubjectName() + "]成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
