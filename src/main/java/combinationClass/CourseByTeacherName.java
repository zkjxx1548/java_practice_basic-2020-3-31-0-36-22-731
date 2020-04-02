package combinationClass;

public class CourseByTeacherName {
    private String courseId;
    private String subjectName;

    public CourseByTeacherName() {
    }

    public CourseByTeacherName(String courseId, String subjectName) {
        this.courseId = courseId;
        this.subjectName = subjectName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public String toString() {
        return "课程编号：" + courseId + "，科目名称：" + subjectName;
    }
}
