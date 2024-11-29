import javax.swing.*;

public class Authenticate extends App {
    public void authenticate(String username, String password) {

        HandleCSV handleCSV = new HandleCSV();
        try {
            handleCSV.read("credentials");
    
            String loginUsername = App.credentials[0];
            String loginPassword = App.credentials[1];
    
            if (username.equals(loginUsername) && password.equals(loginPassword)) {
                isLoggedIn = true;
    
                Gui gui = new Gui();
                gui.AdminScreen();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                isLoggedIn = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            isLoggedIn = false;
        }
    }

    public void logout() {
        isLoggedIn = false;
    }

    public Boolean isAuthenticated() {
        return isLoggedIn;
    }
}
