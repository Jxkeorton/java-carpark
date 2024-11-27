import javax.swing.*;
import java.awt.*;


public class AdminScreenGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private Admin admin;

    public AdminScreenGUI() {
        admin = new Admin();
        setupGui();
        displayData(admin.getData());
    }

    private void setupGui() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanels();
        setupSearch();
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
        String[] newData = new String[contentPanel.getComponentCount()];
        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            JPanel panel = (JPanel) contentPanel.getComponent(i);
            JPanel dataPanel = (JPanel) panel.getComponent(0);
            JLabel label = (JLabel) dataPanel.getComponent(0);
            newData[i] = label.getText();
        }
        admin.updateData(newData);
    }
}