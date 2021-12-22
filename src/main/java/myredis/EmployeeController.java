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
        return employeeService.getEmployee();
    }

    @DeleteMapping("/delete-employee")
    public void deleteAll() {
        employeeService.deleteEmployee();
    }


}
