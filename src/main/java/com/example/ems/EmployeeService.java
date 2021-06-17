package com.example.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeOptional.isPresent()){
            throw new IllegalStateException("Employee already exists.");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        boolean exists =  employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Employee with this Id not found.");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Long employeeId, String name, String email, LocalDate dob){
        Employee employeeToUpdate = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("Employee not found"));
        if (name != null && name.length() > 0 && !Objects.equals(employeeToUpdate.getName(), name))
        {
            employeeToUpdate.setName(name);
            System.out.println("Name did update!");
        }
        if (email != null && name.length() > 0 && !Objects.equals(employeeToUpdate.getEmail(), email))
        {
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
            if (employeeOptional.isPresent()){
                throw new IllegalStateException("Email is in use");
            }
            employeeToUpdate.setEmail(email);
        }

    }
}
