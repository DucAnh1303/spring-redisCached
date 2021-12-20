package myredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    @Cacheable(value = "employeeId", key = "#id")
    public List<Employee> getById(
            @PathVariable(value = "id") int id
    ) {
        return employeeService.getId(id);
    }

    @PostMapping("/save-employee")
    public Employee saveEmployee(
            @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/delete-employee/{id}")
    @CacheEvict(value = "deleteEmployee", key = "#id")
    public List<Employee> deleteEmployee(
            @PathVariable(value = "id") int id
    ) {
        return employeeService.deleteEmployeeBtId(id);
    }
}
