package basicClass;

public class Course {
    private String id;
    private String teacherId;
    private String teacherName;
    private String subjectId;
    private String subjectName;

    public Course() {
    }

    public Course(String id, String teacherName, String subjectName) {
        this.id = id;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
    }

    public Course(String id, String teacherId, String teacherName, String subjectId, String subjectName) {
        this.id = id;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return "课程编号" + id + "，教师名称" + teacherName + "，科目名称" + subjectName;
    }
}
