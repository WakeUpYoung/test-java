package com.wy.test.calender;

import java.util.Calendar;
import java.util.Date;

public class CalenderTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        String date = String.format("当前年：%tY,月 %tm, 日 %td  %tF", now, now, now, now);
        System.out.println(date);
        System.out.println(calendar.get(Calendar.ERA));
    }
}
