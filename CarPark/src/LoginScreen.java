import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

	private JFrame frmAdministratorLogin;
	private JTextField txtUsername;
	private JTextField txtPass;

	public LoginScreen() {

		frmAdministratorLogin = new JFrame();
		frmAdministratorLogin.setTitle("Administrator Login");
		frmAdministratorLogin.setResizable(false);
		frmAdministratorLogin.setBounds(100, 100, 330, 201);

		// gridbag is a form of grid but has more adaptability to allow us to better
		// organise the gui items
		// (https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html)
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 0 };
		frmAdministratorLogin.getContentPane().setLayout(gridBagLayout);

		// label for username
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbcLblUsername = new GridBagConstraints();
		gbcLblUsername.insets = new Insets(0, 0, 5, 5);
		gbcLblUsername.anchor = GridBagConstraints.EAST;
		gbcLblUsername.gridx = 1;
		gbcLblUsername.gridy = 2;
		frmAdministratorLogin.getContentPane().add(lblUsername, gbcLblUsername);

		// textbox for username
		txtUsername = new JTextField();
		GridBagConstraints gbcTxtUsername = new GridBagConstraints();
		gbcTxtUsername.insets = new Insets(0, 0, 5, 5);
		gbcTxtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtUsername.gridx = 2;
		gbcTxtUsername.gridy = 2;
		frmAdministratorLogin.getContentPane().add(txtUsername, gbcTxtUsername);
		txtUsername.setColumns(10);

		// label for password
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbcLblPassword = new GridBagConstraints();
		gbcLblPassword.anchor = GridBagConstraints.EAST;
		gbcLblPassword.insets = new Insets(0, 0, 5, 5);
		gbcLblPassword.gridx = 1;
		gbcLblPassword.gridy = 3;
		frmAdministratorLogin.getContentPane().add(lblPassword, gbcLblPassword);

		// textbox for password - swing component called JPasswordField,
		// https://docs.oracle.com/en/java/javase/23/docs/api/java.desktop/javax/swing/JPasswordField.html
		txtPass = new JPasswordField();
		GridBagConstraints gbcTxtPass = new GridBagConstraints();
		gbcTxtPass.insets = new Insets(0, 0, 5, 5);
		gbcTxtPass.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtPass.gridx = 2;
		gbcTxtPass.gridy = 3;
		frmAdministratorLogin.getContentPane().add(txtPass, gbcTxtPass);

		// Login Button
		JButton btnNew = new JButton("Login");
		GridBagConstraints gbcBtnNew = new GridBagConstraints();
		gbcBtnNew.insets = new Insets(0, 0, 5, 5);
		gbcBtnNew.gridx = 1;
		gbcBtnNew.gridy = 4;
		frmAdministratorLogin.getContentPane().add(btnNew, gbcBtnNew);

		// Action listener for Login button
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtPass.getText();
				String username = txtUsername.getText();

				Authenticate login = new Authenticate();
				boolean loggedIn = login.authenticate(username, password);
				if (loggedIn) {
					frmAdministratorLogin.dispose();
				}
			}
		});

		// Cancel Button
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbcBtnCancel = new GridBagConstraints();
		gbcBtnCancel.anchor = GridBagConstraints.WEST;
		gbcBtnCancel.insets = new Insets(0, 0, 5, 5);
		gbcBtnCancel.gridx = 2;
		gbcBtnCancel.gridy = 4;
		frmAdministratorLogin.getContentPane().add(btnCancel, gbcBtnCancel);

		// Action Listener for Cancel Button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAdministratorLogin.dispose();
			}
		});
	}

	public void main() {
		LoginScreen window = new LoginScreen();
		window.frmAdministratorLogin.setUndecorated(true);
		window.frmAdministratorLogin.setVisible(true);
	}
}