package com.wy.test.time;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JavaEightTime {
    public static void main(String[] args) {
        String startTime = "10:00";
        String endTime = "14:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime nowTime = LocalDateTime.now().toLocalTime();
        LocalTime startLocalTime = LocalTime.parse(startTime, dateTimeFormatter);
        LocalTime endLocalTime = LocalTime.parse(endTime, dateTimeFormatter);
        System.out.println(nowTime);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(nowTime.isAfter(startLocalTime) && nowTime.isBefore(endLocalTime));
    }
}
