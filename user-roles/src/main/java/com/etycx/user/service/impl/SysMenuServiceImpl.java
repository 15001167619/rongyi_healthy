package com.etycx.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.SysMenu;
import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.ISysMenuService;
import com.etycx.user.common.AesUtil;
import com.etycx.user.mapper.SysMenuMapper;
import com.kuding.anno.ExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
@ExceptionListener
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    private final BaseVo baseVo = new BaseVo();

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public BaseVo getMenuList(Map<String, Object> params) {
        String roleIds = (String)params.get("roleIds");
        List<SysMenu> menus = sysMenuMapper.findMenuList(roleIds);
        Map<Integer, List<SysMenu>> menuParent = menus.stream()
                .collect(Collectors.groupingBy(SysMenu::getParentId));
        JSONArray parentData = new JSONArray();
        if( menuParent != null){
            Function<List<SysMenu>,JSONArray> parentFunction = new SysMenuServiceImpl()::menuListInfo;
            //获取父菜单
            parentData = parentFunction.apply(menuParent.get(0));
            for (Object obj : parentData) {
                JSONObject parentInfo = (JSONObject)obj;
                List<JSONObject> childrenData =  new ArrayList<>();
                Integer menuId = parentInfo.getInteger("menuId");
                for (SysMenu childrenInfo : menus) {
                    Integer parentId = childrenInfo.getParentId();
                    if(menuId.equals(parentId)){
                        Function<SysMenu,JSONObject> childrenFunction = new SysMenuServiceImpl()::menuInfo;
                        childrenData.add(childrenFunction.apply(childrenInfo));
                        break;
                    }
                }
                parentInfo.put("childrenData",childrenData);
            }
        }
        System.out.println(parentData.toJSONString());
        System.out.println(AesUtil.aesEncrypt(parentData.toJSONString()));
        return baseVo.ok(AesUtil.aesEncrypt(parentData.toJSONString()),"菜单列表获取成功");
    }

    /**
     * <p>
     *      获取菜单详情
     * </p>
     */
    private JSONObject menuInfo(SysMenu sysMenu) {
        JSONObject parentData = new JSONObject();
        parentData.put("menuId",sysMenu.getMenuId());
        parentData.put("label",sysMenu.getLabel());
        parentData.put("name",sysMenu.getName());
        parentData.put("icon",sysMenu.getIcon());
        parentData.put("path",sysMenu.getPath());
        return parentData;
    }

    private JSONArray menuListInfo(List<SysMenu> list){
        JSONArray menus = new JSONArray();
        for (SysMenu sysMenu : list) {
            menus.add(menuInfo(sysMenu));
        }
        return menus;
    }
}
