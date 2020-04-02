package combinationClass;

public class StudentScore {
    private String studentId;
    private String name;
    private String subjectId;
    private String subject;
    private Double score;

    public StudentScore() {
    }

    public StudentScore(String name, String subject, Double score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public StudentScore(Double score, String studentId, String subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public Double getScore() {
        return score;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return "姓名：" + name + "，学科：" + subject + "，成绩：" + score;
    }
}
