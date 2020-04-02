package basicClass;

public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String password;

    public Student() {
    }

    public Student(String id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student(String id, String name, int age, String gender, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "学号：" + id + "，姓名： " + name + ", 年龄： " + age + ", 性别： " + gender;
    }
}
