package me.pingcai;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * create by 黄平财 at 2017/11/30 23:23
 */
@Data
public class AppTests {
    String name;

    Map<String, Object> map;

    List<Object> list;

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    @Test
    public void testDatetime2Timestamp() {
        System.out.println(System.currentTimeMillis());
        LocalDate date = LocalDate.parse("2017-11-27", FORMATTER);
        LocalDateTime dateTime = date.atStartOfDay();
        System.out.println(dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void testJson() throws JsonProcessingException, UnsupportedEncodingException {
        String str = "中";

        // \xF0\xA7\x9D\x81\xE6\x98

        System.out.println(str.getBytes());

        long decimal = nScale2Decimal("\\xF0\\xA7\\x9D\\x81\\xE6\\x98", 16);


        System.out.println(Long.toBinaryString(decimal));
    }

    public static long nScale2Decimal(String number, int N) {
        long result = 0;
        if (null != number) {
            char[] array = number.replace("\\x", "").toCharArray();
            int len = array.length;
            for (int i = len - 1; i >= 0; i--) {
                result += char2Decimal(array[i]) * Math.pow(N, len - i - 1);
            }
        }
        return result;
    }

    /**
     * 1234567890
     * ABCDEF
     *
     * @param c n进制的数
     * @return 字符对应的十进制
     */
    private static byte char2Decimal(char c) {
        byte result = 0;
        switch (c) {
            case 'A':
            case 'a':
                result = 10;
                break;
            case 'B':
            case 'b':
                result = 11;
                break;
            case 'C':
            case 'c':
                result = 12;
                break;
            case 'D':
            case 'd':
                result = 13;
                break;
            case 'E':
            case 'e':
                result = 14;
                break;
            case 'F':
            case 'f':
                result = 15;
                break;
            default:
                result = (byte) (c - 48);
                break;
        }
        return result;
    }


}
