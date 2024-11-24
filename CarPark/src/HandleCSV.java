import java.io.*;
import java.util.Scanner;

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
                    App.carparkData = new String[]{username, password};
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find Credentials.csv file");
                    System.out.println("Looking in: " + new File("../Credentials.csv").getAbsolutePath());
                    App.carparkData = new String[]{"", ""};
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
                    App.carparkData = data.isEmpty() ? new String[0] : data.split("\n");

                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find Carpark data file");
                    System.out.println("Looking in: " + new File("CarPark/VehicleData.csv").getAbsolutePath());
                    App.carparkData = new String[0];
                }
            } else {
                App.carparkData = new String[0];
            }

        } catch (Exception e) {
            e.printStackTrace();
            App.carparkData = new String[0];
        }
    }
    //Update
    public void update(String[] data) {
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
            System.out.println("File updated successfully");
    
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Delete
    
}
