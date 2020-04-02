import basicClass.Login;
import basicClass.User;
import option.RootOption;

import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    Login login = new Login();
    login.loginPage();
    Scanner scanner = new Scanner(System.in);
    String[] userInfo = scanner.nextLine().split(":");
    login.loginCheck(new User(userInfo[0], userInfo[1]));

    //登录成功
    RootOption rootOption = new RootOption();

    while (scanner.hasNext()) {
      String command = scanner.nextLine();
      rootOption.getCommand(command);
    }


  }

}
