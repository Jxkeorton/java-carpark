import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Vehicle {

    HandleCSV csvHandler = new HandleCSV();

    // enter carpark
    public void enter(String VRN) {
        String formattedDateTime = DateFormatter();
        String[] dateTimeArray = formattedDateTime.split(" ");

        String date = dateTimeArray[0];
        String time = dateTimeArray[1];

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(VRN + ",");
        stringBuilder.append(date + ",");
        stringBuilder.append(time + ",,,");

        App.carparkData.add(stringBuilder.toString());

        // add to carparkData
        try {
            csvHandler.update(App.carparkData);
            JOptionPane.showMessageDialog(null, "Success", "Please enter the carpark", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Csv isn't being updated correctly", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // exit carpark (returns payment amount)
    public Integer exit(String VRN) {
        String exitDateTime = DateFormatter();
        String[] exitDT = exitDateTime.split(" ");

        String rowData = null;
        int rowIndex = -1;
        // Get VRN from array list and entry time/day
        for (int i = 0; i < App.carparkData.size(); i++) {
            if (App.carparkData.get(i).contains(VRN)) {
                rowData = App.carparkData.get(i);
                rowIndex = i;
            }
        }

        String entryDateTime = "";

        if (rowData != null) {
            String[] rowDataArray = rowData.split(",");
            String[] entryDateTimeArray = {rowDataArray[1],rowDataArray[2] };
            entryDateTime = entryDateTimeArray[0] + " " + entryDateTimeArray[1];
        } else {
            return null;
        }

        Payment payment = new Payment();
        int paymentAmmount = payment.payment(entryDateTime, exitDateTime);

        // update csv
        String[] rowElements = rowData.split(",");
        String updatedRow = String.format("%s,%s,%s,%s,%s,%.2f",
            rowElements[0], rowElements[1], rowElements[2],
            exitDT[0], exitDT[1], (double)paymentAmmount);
        
        App.carparkData.set(rowIndex, updatedRow);

        try {
            csvHandler.update(App.carparkData);
        } catch (Exception e) {
            System.out.println("error updating correct VRN on exit");
        }

        return paymentAmmount;
    }

    // date formatter https://java2blog.com/java-localdatetime-to-string/
    public static String DateFormatter() {
        // Get current LocalDateTime
        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        // Create DateTimeFormatter instance with specified format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Format LocalDateTime to String
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        return formattedDateTime;
    }
}
