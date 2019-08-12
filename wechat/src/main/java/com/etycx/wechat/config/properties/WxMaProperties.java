package com.etycx.wechat.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @description
 * @date 2019/5/26 14:07
 */
@Component
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {

    private String appId;

    private String secret;

    private String mchId;

    private String paySignKey;

    private String tradeType;

    private String certName;

    private String notifyUrl;

    private String uniformorder;

    private List<Config> configs;

    public Map<String, Object> getPayConfig() {
        Map<String,Object> payConfig = new HashMap<>(8);
        payConfig.put("appId",appId);
        payConfig.put("secret",secret);
        payConfig.put("mchId",mchId);
        payConfig.put("paySignKey",paySignKey);
        payConfig.put("tradeType",tradeType);
        payConfig.put("certName",certName);
        payConfig.put("notifyUrl",notifyUrl);
        payConfig.put("uniformorder",uniformorder);
        return payConfig;
    }


    @Data
    public static class Config {
        /**
         * 设置微信小程序的appid
         */
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        private String secret;

        /**
         * 设置微信小程序消息服务器配置的token
         */
        private String token;

        /**
         * 设置微信小程序消息服务器配置的EncodingAESKey
         */
        private String aesKey;

        /**
         * 消息格式，XML或者JSON
         */
        private String msgDataFormat;
    }

}
