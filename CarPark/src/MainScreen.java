import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.*;

public class MainScreen extends JFrame {
	JPanel panel = new JPanel();
	JButton enterButton = new JButton("Enter");
	JButton exitButton = new JButton("Exit");
	JTextField textField = new JTextField("Enter VRN", 20);
	JLabel adminLink = new JLabel("Admin");


	Vehicle vehicle = new Vehicle();

	public void main() {
		MainScreen gui = new MainScreen();
		gui.setVisible(true);
	}

	public MainScreen() {
		super("Car park Enter VRN");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);

		Icon linkIcon = UIManager.getIcon("OptionPane.informationIcon");
		adminLink.setIcon(linkIcon);
		adminLink.setText("<html><a href=''>Admin</a></html>");
		adminLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(textField);
		panel.add(enterButton);
		panel.add(exitButton);
		panel.add(adminLink);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vrn = textField.getText();
				vehicle.enter(vrn);
				textField.setText("");
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vrn = textField.getText();
				int payment = vehicle.exit(vrn);
				paymentPane(payment);
				textField.setText("");
			}
		});

		adminLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Gui gui = new Gui();
				Authenticate auth = new Authenticate();
				Boolean isAuthenticated = auth.isAuthenticated();

				System.out.println(isAuthenticated);

				if (isAuthenticated) {
					gui.AdminScreen();
				} else {
					gui.LogInScreen();
				}
			}
		});
	}

	private void paymentPane(int payment){
		// Show the dialog with only the "Pay" button
		Object[] options = {"Pay"};
		int choice = JOptionPane.showOptionDialog(
			null,
			"Amount to pay: £" + payment,
			"Payment",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			options,
			options[0]
		);

		// Handle the user's choice
		if (choice == 0) { // User clicked "Pay"
			JOptionPane.showMessageDialog(null, "Payment successful!");
		}
	}
}