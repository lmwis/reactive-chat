package com.lmwis.util;

import com.lmwis.error.CheckException;

import java.util.stream.Stream;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-03 18:52
 * @Version 1.0
 */
public class CheckUtil {
    private static final String[] INVALID_NAMES = {"admin","guanliyuan"};

    public static void checkName(String value){
        Stream.of(INVALID_NAMES).filter(name->name.equalsIgnoreCase(value))
                .findAny().ifPresent(name->{
                    throw new CheckException("name",value);
        });
    }
}
