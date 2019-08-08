package com.etycx.rest.common.base;

import com.etycx.remote.response.BaseVo;
import com.etycx.rest.common.utils.AesUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @description 基础controller
 * @date 2019/5/14 18:45
 */

public class BaseApiController {

    protected BaseVo isNullData(){
        BaseVo baseVo = new BaseVo();
        return baseVo.isNullError();
    }

    protected BaseVo isErrorSignData(){
        BaseVo baseVo = new BaseVo();
        return baseVo.isErrorSignData();
    }

    protected Map<String, Object> getDataParamsMap(String dataParams){
        Map<String, Object> dataParamsMap = new HashMap<>(1);
        dataParams = AesUtil.aesDecrypt(dataParams);
        if(dataParams.contains("&")){
            dataParamsMap = Stream.of(dataParams.split("&"))
                    .map(str -> str.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        }else {
            String[] split = dataParams.split("=");
            String value = "";
            final int paramsLength = 2;
            if(split.length == paramsLength){
                value = split[1];
            }
            dataParamsMap.put(split[0],value);
        }
        return dataParamsMap;
    }



}
