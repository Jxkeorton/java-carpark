import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

public class Admin {
    private HandleCSV csvHandler;
    private ArrayList<String> carparkData;

    public Admin() {
        csvHandler = new HandleCSV();
        loadData();
    }

    public void loadData() {
        csvHandler.read("carpark");
        carparkData = App.carparkData;
    }

    public String[] searchData(String searchTerm) {
        ArrayList<String> results = new ArrayList<>();
        String term = searchTerm.toLowerCase();
        
        for (String row : carparkData) {
            if (row.toLowerCase().contains(term)) {
                results.add(row);
            }
        }
        
        return results.toArray(new String[0]);
    }

    public void updateData(ArrayList<String> newData) {
        try {
            csvHandler.update(newData);
            loadData();
            JOptionPane.showMessageDialog(null, "Record Updated Successfully", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating data", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<String> getData() {
        return carparkData;
    }
}