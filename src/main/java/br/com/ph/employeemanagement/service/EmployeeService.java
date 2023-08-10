package br.com.ph.employeemanagement.service;

import br.com.ph.employeemanagement.dto.EmployeeDTO;
import br.com.ph.employeemanagement.entity.Employee;
import br.com.ph.employeemanagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public Employee create(EmployeeDTO dto){
        var model = EmployeeMapper.fromDTOToModel(dto);
        return repository.save(model);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Optional<Employee> get(Long id) {
        return repository.findById(id);
    }
}
