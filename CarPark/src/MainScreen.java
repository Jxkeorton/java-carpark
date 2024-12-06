import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.awt.event.*;

public class MainScreen extends JFrame {
    JPanel panel = new JPanel();
    JButton enterButton = new JButton("Enter");
    JButton exitButton = new JButton("Exit");
    JTextField textField = new JTextField(20);
    JLabel vrnLabel = new JLabel("Enter VRN:");
    JLabel adminLink = new JLabel("Admin");

    Vehicle vehicle = new Vehicle();

    public void main() {
        MainScreen gui = new MainScreen();
        gui.setVisible(true);
    }

    public MainScreen() {
        super("Car park Enter VRN");
        setSize(500, 200);
        setUndecorated(true);
        
        // Use BorderLayout for the main frame
        setLayout(new BorderLayout());
        
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); 
        
        JPanel mainComponents = new JPanel();
        mainComponents.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        vrnLabel.setFont(new Font(vrnLabel.getFont().getName(), Font.BOLD, 14));
        
        Icon linkIcon = UIManager.getIcon("OptionPane.informationIcon");
        adminLink.setIcon(linkIcon);
        adminLink.setText("<html><a href=''>Admin</a></html>");
        adminLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        mainComponents.add(vrnLabel);
        mainComponents.add(textField);
        mainComponents.add(enterButton);
        mainComponents.add(exitButton);
        
        wrapperPanel.add(mainComponents, gbc);
        
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        adminPanel.add(adminLink);
        gbc.insets = new Insets(15, 5, 5, 5);
        wrapperPanel.add(adminPanel, gbc);
        
        add(wrapperPanel, BorderLayout.CENTER);

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
                if (payment > 0){
                    paymentPanel(payment);
                    textField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error", "You likely entered the VRN number incorrectly", JOptionPane.INFORMATION_MESSAGE);
                }
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

    private void paymentPanel(int payment){
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

        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "Payment successful!");
        }
    }
}