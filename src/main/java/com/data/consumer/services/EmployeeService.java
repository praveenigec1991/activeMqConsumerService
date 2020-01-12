package com.data.consumer.services;

import com.data.consumer.das.EmployeeDao;
import com.data.consumer.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public boolean save(Employee e)
    {
        return employeeDao.saveEmployee(e);
    }
}
