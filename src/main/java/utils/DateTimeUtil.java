/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class DateTimeUtil {
    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Timestamp timeStamp) {
        return timeStamp.toLocalDateTime();
    }
    public static Timestamp convertToTimestampViaLocalDateTime (LocalDateTime dateTime){
        return Timestamp.valueOf(dateTime);
    }
    public static Date convertToDateViaLocalDate(LocalDate localDate){
        return Date.valueOf(localDate);
    }
    public static String convertToStringViaDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public static String convertToStringViaTimestamp(Timestamp timeStamp){
        Date date = new Date(timeStamp.getTime());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);        
    }
    public static Date convertToDateViaString(String date){
        return Date.valueOf(date);
    }
    public static Timestamp convertToTimestampViaString(String timeStamp){
        return Timestamp.valueOf(timeStamp);
    }
}
