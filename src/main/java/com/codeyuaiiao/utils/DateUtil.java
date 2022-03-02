package com.codeyuaiiao.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * get the current date
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * get the beginning of current date
     */
    public static Date getStartCurrentDate() {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localDateTimeToDate(start);
    }

    /**
     * get the ending of current date
     */
    public static Date getEndCurrentDate() {
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return localDateTimeToDate(end);
    }

    /**
     * get the previous date
     */
    public static Date getPreviousDate(long days) {
        LocalDate localDate = LocalDate.now().minusDays(days);
        return localDateToDate(localDate);
    }

    /**
     * get the future date
     */
    public static Date getFutureDate(long days) {
        LocalDate localDate = LocalDate.now().plusDays(days);
        return localDateToDate(localDate);
    }

    /**
     * LocalDate => Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime => Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date => LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date => LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date => String
     */
    public static String dateToString(Date date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
    }

    /**
     * dateString => Date
     */
    public static Date dateStringToDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * dateTimeString => Date
     */
    public static Date dateTimeStringToDate(String dateTimeStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * dateString => LocalDate
     */
    public static LocalDate dateStringToLocalDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateStr, dateTimeFormatter);
    }

    /**
     * dateTimeString => LocalDate
     */
    public static LocalDateTime dateTimeStringToLocalDateTime(String dateTimeStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
    }
}