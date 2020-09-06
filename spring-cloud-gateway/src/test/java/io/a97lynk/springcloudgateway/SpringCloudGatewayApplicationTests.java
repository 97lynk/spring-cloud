package io.a97lynk.springcloudgateway;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

//@SpringBootTest
class SpringCloudGatewayApplicationTests {

    @Test
    void contextLoads() {

        Calendar calendar = Calendar.getInstance(Locale.GERMAN);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.setMinimalDaysInFirstWeek(1);

        for (int j = 2014; j < 2021; j++) {
            calendar.set(j, 0, 1);
            System.out.println("------------------ " + j);
            for (int i = 0; i < 366; i++) {
                calendar.setFirstDayOfWeek(1);
                calendar.setFirstDayOfWeek(Calendar.MONDAY);

                System.out.printf("%3d = %s%n", calendar.get(Calendar.WEEK_OF_YEAR), calendar.getTime().toString());

                calendar.add(Calendar.DATE, 1);
            }
        }
    }

    @Test
    public void anotherDate() {

        for (int j = 2014; j < 2021; j++) {
            LocalDate date = LocalDate.of(j, 1, 1);
            System.out.println("------------------ " + j);
            for (int i = 0; i < LocalDate.now().withYear(j).lengthOfYear(); i++) {
                System.out.printf("%10s - %s: %2d %2d%n", date.getDayOfWeek(), date.toString(),
                        date.get(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfYear()),
                        date.get(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfWeekBasedYear())
                );
                date = date.plusDays(1);
            }
        }
    }
}
