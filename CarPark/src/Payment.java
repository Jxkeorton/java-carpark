import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    public Integer payment(String entryDateTime, String exitDateTime) {

        int hrlyRate = 3;
        int dailyRate = 15;

        // compare datetime fields to workout payment amount
        // Define the date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Parse the entry and exit date-time strings
        LocalDateTime entryTime = LocalDateTime.parse(entryDateTime, formatter);
        LocalDateTime exitTime = LocalDateTime.parse(exitDateTime, formatter);

        // Calculate the duration between entry and exit times
        Duration duration = Duration.between(entryTime, exitTime);
        long totalMinutes = duration.toMinutes();
        long totalHours = duration.toHours();
        long totalDays = duration.toDays();

        // Calculate the payment amount
        int paymentAmount = 0;

        if (totalDays > 0) {
            paymentAmount += totalDays * dailyRate;
            totalHours -= totalDays * 24;
        }

        if (totalHours > 0) {
            paymentAmount += totalHours * hrlyRate;
        }

        // If there are remaining minutes, charge for an additional hour
        if (totalMinutes % 60 > 0) {
            paymentAmount += hrlyRate;
        }

        return paymentAmount;
    }
}
