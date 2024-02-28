package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceTest {

  private String createUrl;
  private String readUrl;

  @Autowired
  private EmployeeService employeeService;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void setup() {
    createUrl = "http://localhost:" + port + "/compensation";
    readUrl = "http://localhost:" + port + "/compensation/{id}";
  }


  @Test
  public void testCreateRead() {
    Employee testEmployee = new Employee();
    testEmployee.setEmployeeId(UUID.randomUUID().toString());
    testEmployee.setFirstName("John");
    testEmployee.setLastName("Doe");
    testEmployee.setDepartment("Engineering");
    testEmployee.setPosition("Developer");

    Compensation compensation = new Compensation();
    compensation.setEmployee(testEmployee);
    compensation.setEffectiveDate(LocalDate.now());
    compensation.setSalary(new BigDecimal(10000));

    // Create checks
    Compensation createCompensation = restTemplate.postForEntity(createUrl, compensation, Compensation.class).getBody();

    assertNotNull(createCompensation.getEmployee());
    assertEmployeeEquivalence(testEmployee, createCompensation.getEmployee());

    // Read checks
    Compensation compensationFoundByEmployeeId = restTemplate.getForEntity(readUrl, Compensation.class, createCompensation.getEmployee().getEmployeeId()).getBody();

    assertEquals(createCompensation.getEffectiveDate(), compensationFoundByEmployeeId.getEffectiveDate());
    assertCompensationEquivalence(createCompensation, compensationFoundByEmployeeId);
  }

  private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
    assertEquals(expected.getFirstName(), actual.getFirstName());
    assertEquals(expected.getLastName(), actual.getLastName());
    assertEquals(expected.getDepartment(), actual.getDepartment());
    assertEquals(expected.getPosition(), actual.getPosition());
  }

  private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
    assertEquals(expected.getSalary(), actual.getSalary());
    assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());

    assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
  }


}



