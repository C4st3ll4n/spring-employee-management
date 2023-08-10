package br.com.ph.employeemanagement.controller;

import br.com.ph.employeemanagement.dto.EmployeeDTO;
import br.com.ph.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDTO dto, @PathVariable(name = "id") Long id) {
        try {
            var employee = service.get(id);
            if (employee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("None employee registered");
            }

            var updatedEmployee = service.update(dto, id);

            return ResponseEntity.status(HttpStatus.CREATED).body(updatedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado, tente novamente");
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> removeEmployee(@PathVariable(name = "id") Long id) {
        try {
            var employee = service.get(id);
            if (employee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("None employee registered");
            }
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully removed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado, tente novamente");
        }
    }
}
