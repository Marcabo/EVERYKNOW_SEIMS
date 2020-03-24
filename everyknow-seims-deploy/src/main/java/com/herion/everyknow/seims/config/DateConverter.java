package com.herion.everyknow.seims.config;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-12-03 22:38
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换类
 * 将标准日期、标准日期时间、时间戳转换成Date类型
 */
public class DateConverter implements Converter<String, Date> {

    private Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timeStampFormat = "^\\d+$";
    private static final String springTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Override
    public Date convert(String value) {
        logger.info("转换日期：" + value);

        if(value == null || value.trim().equals("") || value.equalsIgnoreCase("null")) {
            return null;
        }

        value = value.trim();

        try {
            if (value.contains("-")) {
                SimpleDateFormat formatter;
                if (value.contains(":")) {
                    if (value.contains("T")) {
                        formatter = new SimpleDateFormat(springTimeFormat);
                    } else {
                        formatter = new SimpleDateFormat(dateFormat);
                    }
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                return formatter.parse(value);
            } else if (value.matches(timeStampFormat)) {
                Long lDate = new Long(value);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", value));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", value));
    }
}
