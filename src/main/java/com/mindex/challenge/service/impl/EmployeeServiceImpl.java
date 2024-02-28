package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee.getDirectReports() != null) {
            for (Employee innerEmployee:
            employee.getDirectReports()) {
                Employee fullInfoEmployee = employeeRepository.findByEmployeeId(
                    innerEmployee.getEmployeeId());
                updateInnerEmployees(innerEmployee, fullInfoEmployee);
            }
        }

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    private void updateInnerEmployees(Employee employeeToBeUpdated, Employee fullInfoEmployee){
        employeeToBeUpdated.setDirectReports(fullInfoEmployee.getDirectReports());
        employeeToBeUpdated.setDepartment(fullInfoEmployee.getDepartment());
        employeeToBeUpdated.setFirstName(fullInfoEmployee.getFirstName());
        employeeToBeUpdated.setPosition(fullInfoEmployee.getPosition());
        employeeToBeUpdated.setLastName(fullInfoEmployee.getLastName());
        if (fullInfoEmployee.getDirectReports() != null) {
            for (Employee subDirectReportEmployee:
                fullInfoEmployee.getDirectReports()) {
                Employee subFullInfoEmployee = employeeRepository.findByEmployeeId(
                    subDirectReportEmployee.getEmployeeId());
                updateInnerEmployees(subDirectReportEmployee, subFullInfoEmployee);
            }
        }
    }
}
