package com.data.consumer.services;

import com.data.consumer.entity.Employee;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;
@Service
public class EmployeeDataConsumerService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDataConsumerService.class);

    @Autowired
    private JmsTemplate template ;

    @Autowired
    private Gson gson ;

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "employee")
    public void receivedEmployeeObjectToMQ(final Message m)
    {

        LOG.info("Message Object {} ",m);
        try {
            if (( m instanceof TextMessage))
            {
                TextMessage textMessage = (TextMessage) m;
                Employee e = gson.fromJson(textMessage.getText(),Employee.class);
                LOG.info("Message converted {} ", e);
                if (employeeService.save(e))
                {
                    LOG.info("Emmployee successfully saved");
                }
                else {
                    LOG.info("Not able to save employee");
                }
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }
}
