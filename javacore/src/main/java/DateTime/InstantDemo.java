package DateTime;

import java.time.Duration;
import java.time.Instant;

public class InstantDemo {

    public static void main(String[] args){
        System.out.println(Math.round(-5/2));
        Instant start = Instant.now();
        System.out.println(start);

        Instant end = Instant.now();
        System.out.println(end);

        // 计算两个时间点的时间间隔
        System.out.println("时间间隔(秒)：" + Duration.between(start, end));
        System.out.println("时间间隔(毫秒)：" + Duration.between(start, end).toMillis());
        System.out.println("时间间隔(纳秒)：" + Duration.between(start, end).toNanos());

    }
}
