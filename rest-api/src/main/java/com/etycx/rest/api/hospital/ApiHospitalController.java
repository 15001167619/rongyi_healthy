package com.etycx.rest.api.hospital;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.response.RemoteServiceException;
import com.etycx.remote.service.IDepartmentService;
import com.etycx.remote.service.IHospitalService;
import com.etycx.rest.common.annotation.Security;
import com.etycx.rest.common.base.BaseApiController;
import com.etycx.rest.common.constant.InterfaceAppKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 武海升
 * @desc 医院管理
 * @date  2019-08-12
 */
@RestController
@RequestMapping("api/auth/hospital")
@Slf4j
public class ApiHospitalController extends BaseApiController {

    @Reference(version = "1.0.0",check = false)
    private IHospitalService hospitalService;

    /**
     * 第一家医院的 科室管理
     */
    @Reference(version = "1.0.0", check = false, group = "health-examination-one")
    private IDepartmentService departmentServiceOne;

    /**
     * 第二家医院的 科室管理
     */
    @Reference(version = "1.0.0", check = false, group = "health-examination-two")
    private IDepartmentService departmentServiceTwo;

    private BaseVo baseVo = new BaseVo();

    /**
     * @description  获取医院列表
     */
    @PostMapping("hospital/hospitalList")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object hospitalList(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = hospitalService.getHospitalList(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  新增医院
     */
    @PostMapping("addHospital")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object addHospital(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = hospitalService.addHospital(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  修改医院
     */
    @PostMapping("updateHospital")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object updateHospital(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = hospitalService.updateHospital(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  删除医院
     */
    @PostMapping("delHospital")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object delHospital(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = hospitalService.delHospital(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  获取医院科室列表
     */
    @PostMapping("1/departmentList")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object departmentList(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = departmentServiceOne.getDepartmentList(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  新增科室
     */
    @PostMapping("addDepartment")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object addDepartment(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = departmentServiceOne.addDepartment(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  修改科室
     */
    @PostMapping("updateDepartment")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object updateDepartment(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = departmentServiceOne.updateDepartment(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }

    /**
     * @description  删除科室
     */
    @PostMapping("delDepartment")
    @Security(appKey = InterfaceAppKeyConstants.HOSPITAL_LIST_APP_KEY,interfaceName = "hospitalList")
    public Object delDepartment(@RequestBody Map<String,Object> params){
        try {
            String dataParams = (String)params.get("dataParams");
            if(StringUtils.isNotEmpty(dataParams)){
                Map<String, Object> dataParamsMap = getDataParamsMap(dataParams);
                baseVo = departmentServiceOne.delDepartment(dataParamsMap);
            }
        } catch (Exception e) {
            throw new RemoteServiceException(501, "远程服务异常");
        }
        return baseVo;
    }



}
