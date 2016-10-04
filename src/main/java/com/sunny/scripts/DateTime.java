package com.sunny.scripts;

import com.sunny.common.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by sunshah on 9/26/16.
 */
public class DateTime {

    public static void main(String[] args) {
        Logger.log("Arguments received:");
        Logger.logList(Arrays.asList(args));
        String inputDate = args[0];
        long publishDate = Long.parseLong(inputDate);
//        long publishDate = 1789242578L;
        epochToDate(publishDate);
//        dateToEpoch();
    }

    public static void epochToDate(long epoch) {
        Date date = new Date(epoch);
        Logger.log(date.toString());
    }

    public static void dateToEpoch() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = "01/01/2016";
        String dateString2 = "01/01/2015";
        try {
            Date date = simpleDateFormat.parse(dateString2);
            Logger.log("epoch => " + date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void epochSubstraction() {
    }
}
