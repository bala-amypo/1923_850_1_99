package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    
    public static boolean isDateInFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }
    
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}