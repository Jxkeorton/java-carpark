import java.util.ArrayList;

public class App {
    Boolean isLoggedIn;;
    static String[] credentials;
    static ArrayList<String> carparkData;
    
    public App() {
        isLoggedIn = false;
        carparkData = new ArrayList<String>();
        credentials = new String[0];
    }

    public static void main(String[] args) throws Exception {
        HandleCSV handleCSV = new HandleCSV();
        Gui gui = new Gui();

        handleCSV.read("carpark");
        gui.MainScreen();

    }
}