package com.etycx.rest.api.wechat;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import com.etycx.remote.service.IWechatService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 武海升
 * @desc 微信支付
 * @date  2019-08-12
 */
@RestController
@RequestMapping("api/pay")
public class ApiPayController {

    @Reference(version = "1.0.0",check = false)
    private IWechatService wechatService;

    private BaseVo baseVo = new BaseVo();

    /**
     * @description  获取支付的请求参数
     */
    @PostMapping("prepay")
    public Object info(@RequestParam(value = "orderId") Integer orderId,HttpServletRequest request){
        try {
            baseVo = wechatService.prepay(orderId,request);
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  微信支付通知
     */
    @RequestMapping(value = "notify")
    public void appNotify(HttpServletRequest request, HttpServletResponse response){
        wechatService.notify(request, response);
    }

}
