/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author Ariel
 */
public class Converter {

    public static LocalDate converterStringToLocalDate(String strLocalDate) {
        DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
        LocalDate datetime = LocalDate.parse(strLocalDate, f);
        return datetime;
    }
    
    public static String converterLocalDateToString(LocalDate ldt) {
        String formattedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }
}
