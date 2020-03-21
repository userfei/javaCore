package DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

// 本地时间
public class LocalDateDemo {

    public static void main(String[] args){

        LocalDate today = LocalDate.now();
        System.out.println("今天 星期: " + today.getDayOfWeek());

        // LocalDate 星期, 星期天（7）
        LocalDate birthDay = LocalDate.of(2018, 12, 17);
        System.out.println("2018-12-17 星期: " + birthDay.getDayOfWeek().getValue());
        System.out.println("2018-12-18 星期: " + birthDay.plusDays(1).getDayOfWeek());
        System.out.println("18号和17号的时间间隔:" + today.until(birthDay));  // P0D

        // LocalTime 本地时间
        LocalTime time = LocalTime.now();
        System.out.println("现在的时间:" + time);

        // Calendar 星期几和地区有关，星期天（1/7）
        Calendar calendar = Calendar.getInstance();
        System.out.println("星期一星期：" + calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DATE, -1);
        System.out.println("昨天星期：" + calendar.get(Calendar.DAY_OF_WEEK));

        // 格式化和解析
        String formatted = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.now());
        System.out.println("日期字符串：" + formatted);
    }
}
