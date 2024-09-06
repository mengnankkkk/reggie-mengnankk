package org.mengnankk.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fubukiss.rikky.entity.Employee;
import org.mengnankk.mapper.EmployeeMapper;
import org.mengnankk.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public boolean saveBatch(Collection<Employee> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Employee> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Employee> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Employee entity) {
        return false;
    }

    @Override
    public Employee getOne(Wrapper<Employee> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Employee> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Employee> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }
    // 你可以在这里添加其他的业务逻辑实现
}
