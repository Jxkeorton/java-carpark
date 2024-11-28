// import javax.swing.*;
// import java.awt.*;

public class Gui extends App {
    public void MainScreen(){
        MainScreen mainScreen = new MainScreen();
        mainScreen.main();
    };

    public void LogInScreen(){
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.main();
    };

    public void AdminScreen() {
        AdminScreen adminScreen = new AdminScreen();
        adminScreen.setVisible(true);
    }

    public void Error() {

    }
};