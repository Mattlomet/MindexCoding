package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import com.mindex.challenge.data.ReportingStructure;
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
public class ReportingStructureTest {

  private String getReporting;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void setup() {
    getReporting = "http://localhost:" + port + "/report/employee/{employeeId}";
  }

  @Test
  public void testReportingStructure() {

    ReportingStructure reportingStructureJohnLennon = restTemplate.getForEntity(getReporting, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
    assertEquals(4, reportingStructureJohnLennon.getNumberOfReports());

    ReportingStructure reportingStructurePaulMcCartney = restTemplate.getForEntity(getReporting, ReportingStructure.class, "b7839309-3348-463b-a7e3-5de1c168beb3").getBody();
    assertEquals(0, reportingStructurePaulMcCartney.getNumberOfReports());

    ReportingStructure reportingStructureRingoStar = restTemplate.getForEntity(getReporting, ReportingStructure.class, "03aa1462-ffa9-4978-901b-7c001562cf6f").getBody();
    assertEquals(2, reportingStructureRingoStar.getNumberOfReports());

  }

}




