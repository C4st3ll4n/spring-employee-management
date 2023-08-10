package br.com.ph.employeemanagement.service;

import br.com.ph.employeemanagement.dto.EmployeeDTO;
import br.com.ph.employeemanagement.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO fromModelToDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail()
        );
    }

    public static Employee fromDTOToModel(EmployeeDTO dto){
        return new Employee(
                dto.id(), dto.firstName(), dto.lastName(), dto.email()
        );
    }
}
