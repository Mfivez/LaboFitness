package be.labofitness.labo_fitness.il.utils;
import be.labofitness.labo_fitness.domain.entity.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Utility class providing methods for date and time manipulation.
 */
@Configuration
@RequiredArgsConstructor
public class LaboFitnessUtil {

    /**
     * Creates a new {@link LocalDateTime} object with the specified year, month, and day.
     *
     * @param year  The year.
     * @param month The month.
     * @param day   The day of the month.
     * @return A new {@link LocalDateTime} object.
     */
    public static LocalDateTime createNewDate(int year, Month month, int day) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,iniTime, iniTime,iniTime);
    }

    /**
     * Creates a new {@link LocalDateTime} object with the specified year, month, day, and hour.
     *
     * @param year  The year.
     * @param month The month.
     * @param day   The day of the month.
     * @param hour  The hour of the day.
     * @return A new {@link LocalDateTime} object.
     */
    public static LocalDateTime createNewDate(int year, Month month, int day, int hour) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, iniTime,iniTime);
    }

    /**
     * Creates a new {@link LocalDateTime} object with the specified year, month, day, hour, and minute.
     *
     * @param year   The year.
     * @param month  The month.
     * @param day    The day of the month.
     * @param hour   The hour of the day.
     * @param minute The minute of the hour.
     * @return A new {@link LocalDateTime} object.
     */
    public static LocalDateTime createNewDate(int year, Month month, int day, int hour, int minute) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, minute,iniTime);
    }

    /**
     * Converts the given {@link LocalDateTime} object to a string representation of the format "day/month/year".
     *
     * @param date The {@link LocalDateTime} object to convert.
     * @return A string representation of the date in the format "day/month/year".
     */
    public static String DateToStringFormatDayMonthValueYear(LocalDateTime date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * Builds a {@link Competition} name using the provided name and date.
     *
     * @param name     The base name of the {@link Competition}.
     * @param dateTime The date and time of the {@link Competition}.
     * @return The constructed {@link Competition} name.
     */
    public static String CompetitionNameIdBuilder(String name, LocalDateTime dateTime) {
        return name + "Of" + DateToStringFormatDayMonthValueYear(dateTime);
    }

    /**
     * Checks if a date falls within the range of two other dates (inclusive).
     *
     * @param date      The date to check.
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return True if the date is between the start and end dates, false otherwise.
     */
    public static boolean isDateBetweenDates(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }



    /**
     * Uses the CurrentThread to get the method name
     *
     * @return String with the method name
     */
    public static String getCurrentMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static Month getMonthEnumFormat(String month) {
        try {
            return Month.of(Integer.parseInt(month));
        }
        catch (NumberFormatException ignored) {}

        return Month.valueOf(month);
    }

}
