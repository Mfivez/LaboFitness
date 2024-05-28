package be.labofitness.labo_fitness.il.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.Month;


@Configuration
@RequiredArgsConstructor
public class LaboFitnessUtil {

    public static LocalDateTime createNewDate(int year, Month month, int day) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,iniTime, iniTime,iniTime);
    }

    public static LocalDateTime createNewDate(int year, Month month, int day, int hour) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, iniTime,iniTime);
    }

    public static LocalDateTime createNewDate(int year, Month month, int day, int hour, int minute) {
        int iniTime = 0;
        return LocalDateTime.of(year, month, day,hour, minute,iniTime);
    }

    public static String DateToStringFormatDayMonthValueYear(LocalDateTime date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    public static String CompetitionNameIdBuilder(String name, LocalDateTime dateTime) {
        return name + "Of" + DateToStringFormatDayMonthValueYear(dateTime);
    }

    // region COMPARE DATE UTILS

    public static boolean isDateBetweenDates(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }

    //endregion
}
