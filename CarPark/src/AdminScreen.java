import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private Admin admin;

    public AdminScreen() {
        admin = new Admin();
        setupGui();
        ArrayList<String> data = admin.getData();
        String[] displayData = data.toArray(new String[0]);

        displayData(displayData);
    }

    private void setupGui() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanels();
        setupSearch();
        setupLogout();
        add(mainPanel);
    }

    private void setupPanels() {
        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupSearch() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
    }

    private void setupLogout() {
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> logout());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        logoutPanel.add(logoutButton);
        logoutPanel.add(exitButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);
    }

    private void performSearch() {
        String searchTerm = searchField.getText();
        displayData(admin.searchData(searchTerm));
    }

    private void displayData(String[] data) {
        contentPanel.removeAll();
        for (String row : data) {
            addRowPanel(row);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void addRowPanel(String rowData) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        rowPanel.setPreferredSize(new Dimension(scrollPane.getViewport().getWidth(), 50));
        
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dataLabel = new JLabel(rowData);
        dataPanel.add(dataLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Buttons(buttonPanel, rowPanel, dataLabel, rowData);
        
        rowPanel.add(dataPanel, BorderLayout.CENTER);
        rowPanel.add(buttonPanel, BorderLayout.EAST);
        
        contentPanel.add(rowPanel);
        contentPanel.revalidate();
    }

    private void Buttons(JPanel buttonPanel, JPanel rowPanel, JLabel dataLabel, String rowData) {
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        
        editButton.addActionListener(e -> {
            String newData = JOptionPane.showInputDialog(this, "Edit data:", rowData);
            if (newData != null) {
                dataLabel.setText(newData);
                updateData();
            }
        });
        
        deleteButton.addActionListener(e -> {
            contentPanel.remove(rowPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
            updateData();
        });
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }
    
    private void updateData() {
        ArrayList<String> newData = new ArrayList<>();

        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            newData.add(null);
        }

        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            JPanel panel = (JPanel) contentPanel.getComponent(i);
            JPanel dataPanel = (JPanel) panel.getComponent(0);
            JLabel label = (JLabel) dataPanel.getComponent(0);
            newData.set(i, label.getText());
        }
        admin.updateData(newData);
    }

    private void logout() {
        // Call the auth.logout method
        Authenticate auth = new Authenticate();
        auth.logout();

        // Close the AdminScreen window
        dispose();
    }
}