package com.example.ems;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

    @RestController
    @RequestMapping(path="api/employee")
    public class EmployeeController {
        private final EmployeeService employeeService;

        @Autowired
        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        public EmployeeService getEmployeeService() {
            return employeeService;
        }

        @GetMapping
        public List<Employee> getAllEmployees() {
            return employeeService.getEmployees();
        }

        @PostMapping
        public void registerEmployee(@RequestBody Employee employee) {
            employeeService.addEmployee(employee);
        }

        @DeleteMapping(path="{employeeId}")
        public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
            employeeService.deleteEmployee(employeeId);
        }

        @PutMapping(path="{employeeId}")
        public void updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dob) {

        }

    }


