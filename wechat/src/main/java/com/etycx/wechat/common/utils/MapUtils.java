package com.etycx.wechat.common.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *      微信管理 获取map中值的工具类,自动进行类型转换
 * </p>
 *
 * @author 武海升
 * @date  2019-08-12
 */
public class MapUtils {

    public static String getString(String key, Map<String, Object> map) {
        if (map == null || key == null)
            throw new IllegalArgumentException();
        if (!map.containsKey(key))
            return null;
        Object value = map.get(key);
        if (value == null)
            return null;
        return value.toString();
    }

    public static String convertMap2Xml(Map<Object, Object> paraMap) {
        StringBuffer xmlStr = new StringBuffer();
        if (paraMap != null) {
            xmlStr.append("<xml>");
            Set<Object> keySet = paraMap.keySet();
            Iterator<Object> keyIte = keySet.iterator();
            while (keyIte.hasNext()) {
                String key = (String) keyIte.next();
                String val = String.valueOf(paraMap.get(key));
                xmlStr.append("<");
                xmlStr.append(key);
                xmlStr.append(">");
                xmlStr.append(val);
                xmlStr.append("</");
                xmlStr.append(key);
                xmlStr.append(">");
            }
            xmlStr.append("</xml>");
        }
        return xmlStr.toString();
    }
}

