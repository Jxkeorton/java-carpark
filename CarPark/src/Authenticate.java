
public class Authenticate extends App {
    public void authenticate(String username, String password) {

        HandleCSV handleCSV = new HandleCSV();
        handleCSV.read("credentials");

        String loginUsername = App.credentials[0];
        String loginPassword = App.credentials[1];

        if (username.equals(loginUsername) && password.equals(loginPassword)) {
            isLoggedIn = true;

            Gui gui = new Gui();
            gui.AdminScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
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
