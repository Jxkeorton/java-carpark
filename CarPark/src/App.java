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
        // Authenticate auth = new Authenticate();
        // auth.handleAuth();

        // HandleCSV CSV = new HandleCSV();
        // CSV.read("carpark");
        
        // for (int i = 0; i < carparkData.length; i++) {
        //     System.out.println("Row " + i + ": " + carparkData[i]);
        // }

        // // Update car details
        // CSV.delete(0);

        Gui gui = new Gui();
        gui.AdminScreen();
    }
}