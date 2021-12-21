package myredis;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(nativeQuery = true,
            value = "select *from  redis.employee")
    List<Employee> getAll();

    @Query(nativeQuery = true,
            value = "select *from redis.employee where employee.id = :id ")
    List<Employee> findById(
            @Param("id") int id
    );

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "delete from redis.employee where employee.id = :id ")
    void deleteById(Integer id);
}
