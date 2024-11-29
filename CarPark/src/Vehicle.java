import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        csvHandler.update(App.carparkData);
    }

    // exit carpark (returns payment amount)
    public Integer exit(String VRN) {
        String exitDateTime = DateFormatter();

        String rowData = null;
        // Get VRN from array list and entry time/day
        for (int i = 0; i < App.carparkData.size(); i++) {
            if (App.carparkData.get(i).contains(VRN)) {
                rowData = App.carparkData.get(i);
            }
        }

        String entryDateTime = "";

        if (rowData != null) {
            String[] rowDataArray = rowData.split(",");
            String[] entryDateTimeArray = {rowDataArray[1],rowDataArray[2] };
            entryDateTime = entryDateTimeArray[0] + " " + entryDateTimeArray[1];
        }

        Payment payment = new Payment();
        int paymentAmmount = payment.payment(entryDateTime, exitDateTime);
        return paymentAmmount;
    }
    // find VRN in carparkData
    // use delete in handleCSV class

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
