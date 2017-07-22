package test.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kakaf
 * company: SiBu
 * create_date: 2017/02/15
 * description :
 */
public class DateUtil {
    public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_TO_SEC_UN_LINE = "yyyyMMdd HHmmss";
    public static String  getCurrDateStr(String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }
}
