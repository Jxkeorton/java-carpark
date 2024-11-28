// import javax.swing.*;
// import java.awt.*;

public class Gui extends App {
    public void MainScreen(){
        MainScreen mainScreen = new MainScreen();
        mainScreen.main();
    };

    public void LogInScreen(){
        Login loginScreen = new Login();
        loginScreen.main();
    };

    public void AdminScreen() {
        AdminScreenGUI adminScreen = new AdminScreenGUI();
        adminScreen.setVisible(true);
    }
};