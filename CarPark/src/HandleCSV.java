import java.io.*;
import java.util.*;
 
public class HandleCSV {
    File file = new File("CarPark/VehicleData.csv");
    File credentialsFile = new File("CarPark/Credentials.csv");
  
    //Read
    public void read(String fileType) {
        try {
            if (fileType.equals("credentials")) {
                System.out.println("fetch credentials csv");
 
                try {
                   
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
    public void update(ArrayList<String> data) throws IOException {
 
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("VRN,EntryDate,EntryTime,ExitDate,ExitTime,Payment\n");
        for (String row : data) {
            writer.write(row + "\n");
        }
        writer.close();
    }
}