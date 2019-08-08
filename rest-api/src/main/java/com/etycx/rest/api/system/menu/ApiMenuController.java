package com.etycx.rest.api.system.menu;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import com.etycx.remote.service.ISysMenuService;
import com.etycx.rest.common.annotation.Security;
import com.etycx.rest.common.base.BaseApiController;
import com.etycx.rest.common.constant.InterfaceAppKeyConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 武海升
 * @desc 菜单管理
 * @date  2019-08-07
 */
@RestController
@RequestMapping("api/auth/menu")
public class ApiMenuController extends BaseApiController {

    @Reference(version = "1.0.0",check = false)
    private ISysMenuService sysMenuService;

    private BaseVo baseVo = new BaseVo();

    /**
     * @description  获取菜单列表
     */
    @PostMapping("menuList")
    @Security(appKey = InterfaceAppKeyConstants.MENU_LIST_APP_KEY,interfaceName = "menuList")
    public Object menuList(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = sysMenuService.getMenuList(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

}
