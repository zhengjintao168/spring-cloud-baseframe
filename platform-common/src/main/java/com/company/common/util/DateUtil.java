package com.company.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: zjt
 * DateTime: 2018/2/18 12:14
 */
public class DateUtil {

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

}