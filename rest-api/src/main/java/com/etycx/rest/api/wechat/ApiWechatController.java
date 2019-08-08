package com.etycx.rest.api.wechat;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import com.etycx.remote.service.IWechatService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 武海升
 * @desc 微信鉴权
 * @date  2019-08-07
 */
@RestController
@RequestMapping("api/wechat")
public class ApiWechatController {

    @Reference(version = "1.0.0",check = false)
    private IWechatService wechatService;

    private BaseVo baseVo = new BaseVo();

    /**
     * @description  微信授权获取用户信息
     */
    @GetMapping("info")
    public Object info(@RequestParam(value = "userType") Integer userType,@RequestParam(value = "sessionKey") String sessionKey,
                       @RequestParam(value = "signature") String signature,@RequestParam(value = "rawData") String rawData,
                       @RequestParam(value = "encryptedData") String encryptedData,@RequestParam(value = "iv") String iv){
        try {
            baseVo = wechatService.userInfo(userType, sessionKey, signature, rawData, encryptedData, iv);
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  微信授权获取用户手机号
     */
    @GetMapping("phone")
    public Object phone(@RequestParam(value = "userType") Integer userType,@RequestParam(value = "sessionKey") String sessionKey,
                       @RequestParam(value = "signature") String signature,@RequestParam(value = "rawData") String rawData,
                       @RequestParam(value = "encryptedData") String encryptedData,@RequestParam(value = "iv") String iv){
        try {
            baseVo = wechatService.phone(userType, sessionKey, signature, rawData, encryptedData, iv);
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

}
