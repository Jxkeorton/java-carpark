import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.*;
 
 
public class EnterVRN extends JFrame{
	JPanel pnl = new JPanel();
	JButton btn1 = new JButton("Enter");
	JButton btn2 = new JButton ("Exit");
	JTextField textField = new JTextField("Enter VRN",20);
	JLabel adminLink = new JLabel ("Admin");
	public EnterVRN(){
		super("Car park Enter VRN");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(pnl);
		setVisible(true);
		Icon linkIcon = UIManager.getIcon("OptionPane.informationIcon");
		adminLink.setIcon(linkIcon);
		adminLink.setText("<html><a href=''>Admin</a></html>");
		adminLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnl.add(textField);
		pnl.add(btn1);
		pnl.add(btn2);
		pnl.add(adminLink);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vrn = textField.getText();
				JOptionPane.showMessageDialog(pnl, "Enter VRN:" + vrn);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		}
		);
		adminLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(pnl, "Admin panel clicked");
			}
		});
 
	
}
	public static void main (String [] args){
		EnterVRN gui = new EnterVRN();
        gui.setVisible(true);
	}
 
}