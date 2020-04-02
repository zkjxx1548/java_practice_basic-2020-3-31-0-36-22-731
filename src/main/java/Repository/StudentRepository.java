package Repository;

import basicClass.Student;
import combinationClass.StudentScore;
import combinationClass.StudentScoreByTeacher;
import option.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public Student queryStudentById(String id) {
        String sql = "SELECT student_id, student_name, age, gender FROM student WHERE student_id = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        Student student = null;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, id);
            rs = pt.executeQuery();
            while (rs.next()) {
                student = new Student(
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return student;
    }

    public List<Student> queryAllStudent() {
        String sql = "SELECT student_id, student_name, age, gender FROM student";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<Student> students = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getInt("age"),
                        rs.getString("gender")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return students;
    }

    public List<StudentScore> queryByStudentIdForScore(String id) {
        String sql = "SELECT t1.student_name, t2.subject_name, t3.score FROM score AS t3 " +
                "INNER JOIN student AS t1 ON t3.student_id = t1.student_id " +
                "INNER JOIN subject AS t2 ON t3.subject_id = t2.subject_id " +
                "WHERE t3.student_id = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<StudentScore> studentScores = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, id);
            rs = pt.executeQuery();
            while (rs.next()) {
                studentScores.add(new StudentScore(
                        rs.getString("student_name"),
                        rs.getString("subject_name"),
                        rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return studentScores;
    }

    public List<StudentScoreByTeacher> queryByTeacherIdForScore(String id) {
        String sql = "SELECT t1.student_name, t3.score FROM score AS t3 " +
                "INNER JOIN student AS t1 ON t3.student_id = t1.student_id " +
                "INNER JOIN course AS t2 ON t3.subject_id = t2.subject_id " +
                "WHERE t2.teacher_id = ?";
        Connection conn;
        PreparedStatement pt;
        ResultSet rs;
        List<StudentScoreByTeacher> studentScoreByTeachers = new ArrayList<>();
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);
            pt.setString(1, id);
            rs = pt.executeQuery();
            while (rs.next()) {
                studentScoreByTeachers.add(new StudentScoreByTeacher(
                        rs.getString("student_name"),
                        rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtil.closeJDBC(rs, pt, conn);
        return studentScoreByTeachers;
    }

    public void save(Student student) {
        String sql = "INSERT INTO student(student_id, student_name, age, gender) VALUES (?,?,?,?)";
        Connection conn;
        PreparedStatement pt;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);

            pt.setString(1, student.getId());
            pt.setString(2,student.getName());
            pt.setInt(3, student.getAge());
            pt.setString(4, student.getGender());
            pt.executeUpdate();
            pt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("添加学生[" + student.getName() + "-" + student.getId() + "]成功！");
    }

    public void updateScore(StudentScore studentScore) {
        String sql = "UPDATE score SET score = ? WHERE student_id = ? AND subject_id = ?";
        Connection conn;
        PreparedStatement pt;
        try {
            conn = DbUtil.getConnection();
            pt = conn.prepareStatement(sql);

            pt.setDouble(1, studentScore.getScore());
            pt.setString(2, studentScore.getStudentId());
            pt.setString(3, studentScore.getSubjectId());
            pt.executeUpdate();
            pt.close();
            conn.close();
            System.out.println("修改成绩[" + studentScore.getStudentId() + "-" + studentScore.getSubjectId() + "-" + studentScore.getScore() + "]成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String id) {
        Connection conn;
        PreparedStatement pt;
        Student student = queryStudentById(id);
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM student WHERE student_id = ?";
            pt = conn.prepareStatement(sql);

            pt.setString(1, id);
            pt.executeUpdate();
            pt.close();
            conn.close();
            System.out.println("删除学生["+ student.getName() + "-" + id + "]成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
