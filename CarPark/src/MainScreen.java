import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.*;

public class MainScreen extends JFrame {
	JPanel panel = new JPanel();
	JButton enterButton = new JButton("Enter");
	JButton exitButton = new JButton("Exit");
	JTextField textField = new JTextField("Enter VRN", 20);
	JLabel adminLink = new JLabel("Admin");

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
				JOptionPane.showMessageDialog(panel, "Enter VRN:" + vrn);
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
}