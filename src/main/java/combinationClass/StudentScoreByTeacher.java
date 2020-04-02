package combinationClass;

public class StudentScoreByTeacher {
    private String name;
    private Double score;

    public StudentScoreByTeacher() {
    }

    public StudentScoreByTeacher(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "姓名：" + name + "，成绩：" + score;
    }
}
