package com.data.consumer.das;

import com.data.consumer.entity.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jms.Session;

@Repository
public class EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean saveEmployee(Employee e)
    {
        Employee obj = (Employee)sessionFactory.getCurrentSession().merge(e);
        return obj != null ;
    }
}
