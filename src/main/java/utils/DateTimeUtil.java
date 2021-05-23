/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.sql.Timestamp;
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
}
