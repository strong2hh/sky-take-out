package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return Employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     */
    void addEmployee(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return PageResult
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工
     * @param status
     * @param id
     */
    void startotstop(Integer status, long id);

    /**
     * 根据Id查询员工
     * @return
     */
     Employee searchEmployeeById(Integer id);

    /**
     * 更新员工信息
     * @param employeeDTO
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}
