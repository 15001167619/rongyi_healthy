package com.etycx.rest.common.utils;

import java.util.UUID;

/**
 * @author 武海升
 * @desc UuidUtils
 * @date  2019-08-07
 */
public class UuidUtils {

    public static String uuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        System.out.println(UuidUtils.uuid().replace("-",""));
    }

}
