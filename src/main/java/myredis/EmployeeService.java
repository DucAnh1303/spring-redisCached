package myredis;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("redisUICluster")
    private RedisTemplate<String, String> template;


    private Gson gson = null;

    @PostConstruct
    public void init() {
        gson = new Gson();
    }

    public List<Employee> getEmployee() {
        List<Employee> employeeList;
        String key = "employee";
        long start = System.currentTimeMillis();
        String resultRedis = template.opsForValue().get(key);
        if (StringUtils.isEmpty(resultRedis)) {
            employeeList = employeeRepository.findAll();
            template.opsForValue().set(key, gson.toJson(employeeList));
            template.expire(key, 2, TimeUnit.HOURS);
            System.out.println("MYSQL");
            System.out.println(System.currentTimeMillis() - start);
        } else {
            employeeList = gson.fromJson(resultRedis, new TypeToken<ArrayList<Employee>>() {
            }.getType());
            System.out.println("REDIS");
            System.out.println(System.currentTimeMillis() - start);
        }
        return employeeList;
    }

    public void deleteEmployee() {
        String key = "employee";
        template.opsForValue().getOperations().delete(key);
        System.out.println("SUCCESS");
    }
}
