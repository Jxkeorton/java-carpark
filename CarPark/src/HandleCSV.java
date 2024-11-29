import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class HandleCSV {

    //Create

    //Read
    public void read(String fileType) {
        try {
            if (fileType.equals("credentials")) {
                System.out.println("fetch credentials csv");

                try {
                    File credentialsFile = new File("CarPark/Credentials.csv");
                    Scanner myReader = new Scanner(credentialsFile);

                    String password = "";
                    String username = "";

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String[] dataArray = data.split(",");

                        username = dataArray[0];
                        password = dataArray[1];
                    }

                    myReader.close();
                    App.credentials = new String[]{username, password};
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find Credentials.csv file");
                    System.out.println("Looking in: " + new File("../Credentials.csv").getAbsolutePath());
                    App.credentials = new String[]{"", ""};
                }

            } else if (fileType.equals("carpark")) {
                System.out.println("fetch carpark csv");

                try {
                    File vehiclesFile = new File("CarPark/VehicleData.csv");
                    Scanner myReader = new Scanner(vehiclesFile);
                    StringBuilder carparkData = new StringBuilder();
                    
                    if (myReader.hasNextLine()) {
                        myReader.nextLine();
                    }

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        carparkData.append(data).append("\n");
                    }
                    
                    myReader.close();

                    String data = carparkData.toString().trim();
                    App.carparkData = data.isEmpty() ? new ArrayList<String>() : new ArrayList<>(Arrays.asList(data.split("\n")));

                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find Carpark data file");
                    System.out.println("Looking in: " + new File("CarPark/VehicleData.csv").getAbsolutePath());
                    App.carparkData = new ArrayList<String>();
                }
            } else {
                App.carparkData = new ArrayList<String>();
            }

        } catch (Exception e) {
            e.printStackTrace();
            App.carparkData = new ArrayList<String>();
        }
    }
    //Update
    public void update(ArrayList<String> data) {
        try {
            File file = new File("CarPark/VehicleData.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    
            // Write header
            writer.write("VRN,EntryDate,EntryTime,ExitDate,ExitTime,Payment\n");
    
            // Write data
            for (String row : data) {
                writer.write(row + "\n");
            }
    
            writer.close();
            JOptionPane.showMessageDialog(null, "Record Updated Successfully", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating file: " + e.getMessage(), "File Update Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
