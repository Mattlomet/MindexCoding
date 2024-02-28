# Documentation for Task 1 & Task 2


### Compensation endpoint(s)
```
* CREATE
    * HTTP Method: POST
    * URL: localhost:8080/compensation
    * PAYLOAD: Compensation
    * RESPONSE: Compensation
* READ
    * HTTP Method: GET
    * URL: localhost:8080/compensation/{employeeId}
    * RESPONSE: Compensation
```
##### The Compensation object has a JSON schema of:
```json
{
  "type": "Compensation",
  "properties": {
    "employee": {
      "type": "object",
      "properties": {
        "employeeId": {
          "type": "string"
        },
        "firstName": {
          "type": "string"
        },
        "lastName": {
          "type": "string"
        },
        "position": {
          "type": "string"
        },
        "department": {
          "type": "string"
        },
        "directReports": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "employeeId": {
                "type": "string"
              },
              "firstName": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              },
              "position": {
                "type": "string"
              },
              "department": {
                "type": "string"
              }
            }
          }
        }
      }
    },
    "salary": {
      "type": "number",
      "format": "decimal"
    },
    "effectiveDate": {
      "type": "string",
      "format": "date"
    }
  }
}
```

### ReportingStructure endpoint
```
* READ
    * HTTP Method: GET
    * URL: localhost:8080/report/employee/{employeeId}
    * RESPONSE: ReportingStructure
```
##### The ReportingStructure object has a JSON schema of:
```json
{
  "type": "ReportingStructure",
  "properties": {
    "employee": {
      "type": "object",
      "properties": {
        "employeeId": {
          "type": "string"
        },
        "firstName": {
          "type": "string"
        },
        "lastName": {
          "type": "string"
        },
        "position": {
          "type": "string"
        },
        "department": {
          "type": "string"
        },
        "directReports": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "employeeId": {
                "type": "string"
              },
              "firstName": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              },
              "position": {
                "type": "string"
              },
              "department": {
                "type": "string"
              }
            }
          }
        }
      }
    },
    "numberOfReports": {
      "type": "integer"
    }
  }
}
```


