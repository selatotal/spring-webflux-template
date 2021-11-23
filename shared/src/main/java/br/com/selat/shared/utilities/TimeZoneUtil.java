package br.com.selat.shared.utilities;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class TimeZoneUtil {

    public static Long timezoneFromDateTime(int year, Month month, int dayOfMonth, int hour, int minute, int second){
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second).toEpochSecond(ZoneOffset.UTC);
    }


}
