public class App {
    Boolean isLoggedIn;
    static String[] carparkData;
    static String[] credentials;
    
    public App() {
        isLoggedIn = false;
        carparkData = new String[0];
        credentials = new String[0];
    }

    public static void main(String[] args) throws Exception {
        Gui gui = new Gui();
        gui.MainScreen();
    }
}