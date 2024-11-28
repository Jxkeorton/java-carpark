import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {

    HandleCSV csvHandler = new HandleCSV();

    // enter carpark
    public void enter(String VRN) {
        String[] dateTimeArray = DateFormatter();
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

    // exit carpark
    // find VRN in carparkData
    // use delete in handleCSV class

    // date formatter https://java2blog.com/java-localdatetime-to-string/

    public static String[] DateFormatter() {
        // Get current LocalDateTime
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
 
        // Create DateTimeFormatter instance with specified format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
 
        // Format LocalDateTime to String
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        String[] dateTimeArray = formattedDateTime.split(" ");
 
        return dateTimeArray;
    }
}
