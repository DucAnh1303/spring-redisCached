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

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/list-employee")
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @GetMapping("/id")
    public List<Employee> getById(
            @RequestParam(value = "id") int id
    ) {
        return employeeService.getId(id);
    }

    @PostMapping("/save-employee")
    public Employee saveEmployee(
            @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/delete-employee/id")
    public List<Employee> deleteById(
            @RequestParam("id") int id
    ) {
      return employeeService.deleteEmployeeId(id);
    }
}
