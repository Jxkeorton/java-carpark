import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private Admin admin;
    private ButtonGroup filterGroup;
    private JRadioButton showAllRadio;
    private JRadioButton showCurrentlyParkedRadio;

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
        setupTopPanel();
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

    private void setupTopPanel() {
        // Create a panel for the top section with FlowLayout
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterGroup = new ButtonGroup();
        
        showAllRadio = new JRadioButton("Show All", true);
        showCurrentlyParkedRadio = new JRadioButton("Currently in Carpark");
        
        filterGroup.add(showAllRadio);
        filterGroup.add(showCurrentlyParkedRadio);
        
        filterPanel.add(new JLabel("Filter: "));
        filterPanel.add(showAllRadio);
        filterPanel.add(showCurrentlyParkedRadio);
        
        // Add action listeners to radio buttons
        showAllRadio.addActionListener(e -> applyFilters());
        showCurrentlyParkedRadio.addActionListener(e -> applyFilters());
        
        // Add panels to top panel
        topPanel.add(searchPanel);
        topPanel.add(filterPanel);
        
        // Add top panel to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
    }

    private void applyFilters() {
        ArrayList<String> filteredData = new ArrayList<>();
        ArrayList<String> allData = admin.getData();
        
        for (String row : allData) {
            String[] fields = row.split(",");
            boolean hasExitDate = fields.length >= 4 && !fields[3].trim().isEmpty();
            
            if (showAllRadio.isSelected() ||
                (showCurrentlyParkedRadio.isSelected() && !hasExitDate)) {
                filteredData.add(row);
            }
        }
        
        displayData(filteredData.toArray(new String[0]));
    }

    private void performSearch() {
        String searchTerm = searchField.getText();
        String[] searchResults = admin.searchData(searchTerm);
        ArrayList<String> filteredResults = new ArrayList<>();
        
        for (String row : searchResults) {
            String[] fields = row.split(",");
            boolean hasExitDate = fields.length >= 4 && !fields[3].trim().isEmpty();
            
            if (showAllRadio.isSelected() ||
                (showCurrentlyParkedRadio.isSelected() && !hasExitDate)) {
                filteredResults.add(row);
            }
        }
        
        displayData(filteredResults.toArray(new String[0]));
    }

    // Rest of the existing methods remain the same
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
        Authenticate auth = new Authenticate();
        auth.logout();
        dispose();
    }
}