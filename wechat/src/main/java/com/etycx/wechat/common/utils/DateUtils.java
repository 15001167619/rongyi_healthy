package com.etycx.wechat.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <p>
 *      微信管理 日期工具类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-12
 */
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String timeToStr(Long time, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (time.toString().length() < 13) {
            time = time * 1000L;
        }
        Date date = new Date(time);
        String value = dateFormat.format(date);
        return value;
    }

}
