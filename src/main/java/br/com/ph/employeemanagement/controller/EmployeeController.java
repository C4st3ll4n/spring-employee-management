package br.com.ph.employeemanagement.controller;

import br.com.ph.employeemanagement.dto.EmployeeDTO;
import br.com.ph.employeemanagement.entity.Employee;
import br.com.ph.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employee")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDTO dto) {
        try {
            var createdEmployee = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado, tente novamente");
        }

    }

    @GetMapping("/employee")
    public ResponseEntity<Object> getAll() {
        try {
            var employes = service.getAll();
            if (employes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("None employee registered");
            }
            return ResponseEntity.status(HttpStatus.OK).body(employes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado, tente novamente");
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(name = "id") Long id) {
        try {
            var employee = service.get(id);
            if (employee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("None employee registered");
            }
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado, tente novamente");

        }
    }
}
