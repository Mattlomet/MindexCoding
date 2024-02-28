package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {

  private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/report/employee/{employeeId}")
  public ReportingStructure getReportingStructure(@PathVariable String employeeId) {
    LOG.debug("Received ReportingStructure read request for [{}]", employeeId);

    Employee employee = employeeService.read(employeeId);

    int numberOfReports = calculateNumberOfReports(employee);

    return new ReportingStructure(employee,numberOfReports);
  }


  private int calculateNumberOfReports(Employee inputEmployee){
    int numberOfReports = 0;
    if (inputEmployee.getDirectReports() != null) {
      for (Employee employee:
          inputEmployee.getDirectReports()) {
        numberOfReports += 1 + calculateNumberOfReports(employee);
      }
    }
    return numberOfReports;
  }





}
