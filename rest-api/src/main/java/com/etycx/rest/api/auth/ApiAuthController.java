package com.etycx.rest.api.auth;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import com.etycx.remote.service.IAuthUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 武海升
 * @desc 用户安全
 * @date  2019-08-07
 */
@RestController
@RequestMapping("api/auth")
public class ApiAuthController {

    @Reference(version = "1.0.0",check = false)
    private IAuthUserService authUserService;

    private BaseVo baseVo = new BaseVo();

    /**
     * @description  获取token
     */
    @PostMapping("getToken")
    public Object getToken(@RequestBody Map<String,Object> params){
        try {
            baseVo = authUserService.createAuthenticationToken(params);
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

}
