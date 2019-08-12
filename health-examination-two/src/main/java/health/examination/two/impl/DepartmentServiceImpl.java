package health.examination.two.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.Department;
import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.IDepartmentService;
import com.kuding.anno.ExceptionListener;
import health.examination.two.mapper.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *      科室管理 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-12
 */
@Service(version = "${dubbo.version}",group = "${dubbo.group}")
@Component
@Slf4j
@ExceptionListener
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {


    @Override
    public BaseVo getDepartmentList(Map<String, Object> params) {
        log.info("体检二");
        return null;
    }

    @Override
    public BaseVo addDepartment(Map<String, Object> params) {
        return null;
    }

    @Override
    public BaseVo updateDepartment(Map<String, Object> params) {
        return null;
    }

    @Override
    public BaseVo delDepartment(Map<String, Object> params) {
        return null;
    }
}
