import java.util.*;

public class Admin {
    private HandleCSV csvHandler;
    private String[] carparkData;

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

    public void updateData(String[] newData) {
        csvHandler.update(newData);
        loadData();
    }

    public String[] getData() {
        return carparkData;
    }
}