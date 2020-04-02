package basicClass;

import Repository.LoginRepository;

import java.util.Objects;

public class Login {
    public void loginPage() {
        System.out.println("您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：");
    }

    public void loginFailPage() {
        System.out.println("用户名或者密码错误，请输入您的用户名和密码(用户名:密码)：");
    }

    public void loginCheck(User user) {
        LoginRepository loginRepository = new LoginRepository();
        if (Objects.equals(
                loginRepository.queryByNameForPassword(user.getName()),
                user.getPassword())
        ) {
            RootOption.rootOptionPage();
        } else {
            loginFailPage();
        }
    }
}
