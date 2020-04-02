package option;

import basicClass.Course;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import basicClass.Student;
import combinationClass.StudentScore;

import java.util.Objects;
import java.util.Scanner;

public class RootOption {
    public static void rootOptionPage() {
        System.out.println("您好，超级管理员，请选择你需要进行的操作：\n" +
                "    1. 查询   \n" +
                "      1.1 查询学生信息以及成绩  \n" +
                "        1.1.1 所有学生信息  \n" +
                "        1.1.2 指定学生姓名的信息以及所有课程的成绩  \n" +
                "        1.1.3 指定老师的所有学生及其成绩  \n" +
                "      1.2 查询课程信息  \n" +
                "        1.2.1 所有课程信息  \n" +
                "        1.2.2 指定课程名称的信息  \n" +
                "        1.2.3 指定老师的所有课程信息   \n" +
                "      1.3 查询老师信息  \n" +
                "        1.3.1 所有老师信息  \n" +
                "        1.3.2 指定老师信息  \n" +
                "    2. 新增  \n" +
                "      2.1 新增学生信息  \n" +
                "      2.2 新增课程信息     \n" +
                "    3. 修改    \n" +
                "      3.1 修改指定学生的成绩  \n" +
                "    4. 删除  \n" +
                "      4.1 删除指定学生  \n" +
                "      4.2 删除指定课程  \n" +
                "      4.3 删除指定老师 ");
    }

    public void getCommand(String command) {
        switch (command.charAt(0)) {
            case '1' :
                //System.out.println("查询");
                query(command);
                break;
            case '2' :
                //System.out.println("增");
                add(command);
                break;
            case '3' :
                //System.out.println("修改");
                update(command);
                break;
            case '4' :
                //System.out.println("删除");
                delete(command);
        }
    }

    public void query(String command) {
        switch (command.charAt(2)) {
            case '1' :
                //System.out.println("查询学生");
                queryStudent(command);
                break;
            case '2' :
                //System.out.println("查询课程");
                queryCourse(command);
                break;
            case '3' :
                //System.out.println("查询老师");
                queryTeacher(command);
        }
    }

    public void queryStudent(String command) {
        switch (command.charAt(4)) {
            case '1' :
                //System.out.println("所有学生信息  ");
                Tool.printList(new StudentRepository().queryAllStudent());
                RootOption.rootOptionPage();
                break;
            case '2' :
                //System.out.println("指定学生姓名的信息以及所有课程的成绩 ");
                Tool.printList(new StudentRepository().queryByStudentIdForScore("1001"));
                RootOption.rootOptionPage();
                break;
            case '3' :
                //System.out.println("指定老师的所有学生及其成绩");
                Tool.printList(new StudentRepository().queryByTeacherIdForScore("001"));
                RootOption.rootOptionPage();
        }
    }

    public void queryCourse(String command) {
        switch (command.charAt(4)) {
            case '1' :
                //System.out.println("所有课程信息");
                Tool.printList(new CourseRepository().queryAllCourse());
                RootOption.rootOptionPage();
                break;
            case '2' :
                //System.out.println("指定课程名称的信息");
                System.out.println(new CourseRepository().queryCourseById("c_001"));
                RootOption.rootOptionPage();
                break;
            case '3' :
                //System.out.println("指定老师的所有课程信息");
                Tool.printList(new CourseRepository().queryCourseByTeacherName("张老师"));
                RootOption.rootOptionPage();
        }
    }

    public void queryTeacher(String command) {
        switch (command.charAt(4)) {
            case '1' :
                //System.out.println("所有老师信息");
                Tool.printList(new TeacherRepository().queryAllTeacher());
                RootOption.rootOptionPage();
                break;
            case '2' :
                //System.out.println("指定老师信息");
                System.out.println(new TeacherRepository().queryTeacherById("001"));
                RootOption.rootOptionPage();
        }
    }

    public void add(String command) {
        switch (command.charAt(2)) {
            case '1' :
                //System.out.println("新增学生信息");
                addStudent();
                break;
            case '2' :
                //System.out.println("新增课程信息");
                addCourse();
        }
    }

    public void addStudent() {
        System.out.println("请输入学生信息(例如：学号：1001，姓名：小明，年龄：18，性别：男)：");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("，");
        new StudentRepository().save(new Student(
                input[0].split("：")[1],
                input[1].split("：")[1],
                Integer.parseInt(input[2].split("：")[1]),
                input[3].split("：")[1])
        );
        RootOption.rootOptionPage();
    }

    public void addCourse() {
        System.out.println("请输入课程信息(例如：课程编号：c_004，教师编号：001，教师名称：张老师，学科编号：C003，学科名称：英语)：");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("，");
        new CourseRepository().save(new Course(
                input[0].split("：")[1],
                input[1].split("：")[1],
                input[2].split("：")[1],
                input[3].split("：")[1],
                input[4].split("：")[1])
        );
        RootOption.rootOptionPage();
    }

    public void update(String command) {
        if (Objects.equals(command.charAt(2), '1')) {//System.out.println("修改指定学生的成绩");
            updateStudent();
        }
    }

    public void updateStudent() {
        System.out.println("请输入修改分数信息(例如：学生学号：1001，学科编号：C001，分数：85.5)：");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("，");
        new StudentRepository().updateScore(new StudentScore(
                Double.parseDouble(input[2].split("：")[1]),
                input[0].split("：")[1],
                input[1].split("：")[1]
                )
        );
        RootOption.rootOptionPage();
    }

    public void delete(String command) {
        switch (command.charAt(2)) {
            case '1' :
                //System.out.println("删除指定学生");
                deleteStudent();
                RootOption.rootOptionPage();
                break;
            case '2' :
                //System.out.println("删除指定课程");
                deleteCourse();
                RootOption.rootOptionPage();
                break;
            case '3' :
                //System.out.println("删除指定老师");
                deleteTeacher();
                RootOption.rootOptionPage();
        }
    }

    public void deleteStudent() {
        System.out.println("请输入您需要删除的学生学号：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("删除学生之后，该学生信息将不能恢复，是否要继续删除？");
        System.out.println("1. 是\n" +
                "2.否");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1 :
                new StudentRepository().delete(input);
                break;
            case 2 :
                RootOption.rootOptionPage();
        }
    }

    public void deleteCourse() {
        System.out.println("请输入您需要删除的课程编号：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("删除课程之后，该课程信息将不能恢复，是否要继续删除？");
        System.out.println("1. 是\n" +
                "2.否");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1 :
                new CourseRepository().delete(input);
                break;
            case 2 :
                RootOption.rootOptionPage();
        }
    }

    public void deleteTeacher() {
        System.out.println("请输入您需要删除的老师编号：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("删除老师之后，该老师信息将不能恢复，是否要继续删除？");
        System.out.println("1. 是\n" +
                "2.否");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1 :
                new TeacherRepository().delete(input);
                break;
            case 2 :
                RootOption.rootOptionPage();
        }
    }
}
