public class Gui extends App {
    public void MainScreen(){
        MainScreen mainScreen = new MainScreen();
        mainScreen.main();
    };

    public void LogInScreen(){
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    };

    public void AdminScreen() {
        AdminScreen adminScreen = new AdminScreen();
        adminScreen.setVisible(true);
    }
};